/**
 * Copyright © 2018 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jcustenborder.netty.paloalto;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jcustenborder.netty.syslog.RFC3164Message;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public abstract class PaloAltoParserTest<T extends PaloAltoMessage, P extends PaloAltoParser<T>> {
  protected abstract P parser();

  protected ObjectMapper mapper;
  protected PaloAltoMessageDecoder decoder;
  protected File inputRoot;
  protected File[] inputFiles;

  @BeforeEach
  public void setup() {
    this.mapper = new ObjectMapper();
    this.mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    this.mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
    P parser = parser();
    this.decoder = new PaloAltoMessageDecoder(parser);

    this.inputRoot = new File(
        "src/test/resources/com/github/jcustenborder/netty/syslog",
        parser.logType().toLowerCase()
    );
    this.inputFiles = this.inputRoot.listFiles((dir, name) -> name.endsWith(".json"));
  }

  protected T parse(RFC3164Message message) throws Exception {
    ChannelHandlerContext channelHandlerContext = mock(ChannelHandlerContext.class);
    List<Object> output = new ArrayList<>();
    this.decoder.decode(channelHandlerContext, message, output);
    assertFalse(output.isEmpty(), "output should not be empty");
    return (T) output.get(0);
  }
}