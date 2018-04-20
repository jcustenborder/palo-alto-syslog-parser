/**
 * Copyright © 2018 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jcustenborder.netty.paloalto;

import com.github.jcustenborder.netty.syslog.Message;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@ChannelHandler.Sharable
public class PaloAltoMessageHandler extends SimpleChannelInboundHandler<Message> {
  private static final Logger log = LoggerFactory.getLogger(PaloAltoMessageHandler.class);
  final Map<String, PaloAltoParser> parsers;
  final CSVParser csvParser;

  public PaloAltoMessageHandler() {
    this(Arrays.asList(
        new AuthenticationLogParser(),
        new ConfigLogParser(),
        new HipMatchLogParser(),
        new ThreatLogParser(),
        new TrafficLogParser(),
        new UserIdLogParser(),
        new SystemLogParser()
    ));
  }

  @Override
  protected void channelRead0(ChannelHandlerContext context, Message message) throws Exception {
    context.executor().execute(() -> {
      try {
        log.trace("decode() - message = '{}'", message);
        final String[] fields = csvParser.parseLine(message.message());
        final String logType = fields[3];
        log.trace("decode() - logType = '{}'", logType);

        PaloAltoParser parser = parsers.get(logType);
        if (null != parser) {
          log.trace("decode() - Parsing message with {}.", parser.getClass().getSimpleName());
          PaloAltoMessage paloAltoMessage = parser.parse(message, fields);
          if (null != paloAltoMessage) {
            context.fireChannelRead(paloAltoMessage);
          }
        } else {
          log.warn("decode() - Could not find parser. logType = '{}'.", logType);
        }
      } catch (IOException ex) {
        log.error("Exception thrown", ex);
      }
    });
  }

  public PaloAltoMessageHandler(PaloAltoParser... parsers) {
    this(Arrays.asList(parsers));
  }

  public PaloAltoMessageHandler(List<PaloAltoParser> parsers) {
    if (null == parsers || parsers.isEmpty()) {
      throw new IllegalStateException("parsers cannot be null or empty.");
    }
    this.parsers = parsers.stream().collect(Collectors.toMap(PaloAltoParser::logType, Function.identity()));
    this.csvParser = new CSVParserBuilder()
        .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
        .withEscapeChar('\\')
        .withQuoteChar('"')
        .withSeparator(',')
        .build();
  }
}
