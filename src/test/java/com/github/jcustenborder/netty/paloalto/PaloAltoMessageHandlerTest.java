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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jcustenborder.netty.syslog.BSDSyslogMessage;
import com.github.jcustenborder.netty.syslog.RFC3164MessageParser;
import com.github.jcustenborder.netty.syslog.SyslogRequest;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaloAltoMessageHandlerTest {
  private static final Logger log = LoggerFactory.getLogger(PaloAltoMessageHandlerTest.class);
  PaloAltoMessageHandler decoder;
  RFC3164MessageParser parser;
  SyslogRequest request;
  File outputRoot = new File("src/test/resources/com/github/jcustenborder/netty/syslog");
  ObjectMapper mapper;
  ChannelHandlerContext context;
  @BeforeEach
  public void before() throws UnknownHostException {
    this.parser = new RFC3164MessageParser();
    this.request = mock(SyslogRequest.class);
    when(this.request.receivedDate()).thenReturn(new Date(1519755720000L));
    when(this.request.remoteAddress()).thenReturn(InetAddress.getByName("127.0.0.1"));
    this.decoder = new PaloAltoMessageHandler();
    this.mapper = new ObjectMapper();
    this.mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    this.mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
  }

  @Test
  public void noParsers() {
    assertThrows(IllegalStateException.class, () -> {
      new PaloAltoMessageHandler(Collections.emptyList());
    });
  }

//  @Disabled
//  @Test
//  public void generateTestData() throws IOException {
//    int index = 0;
//    List<String> lines = Files.readLines(new File("samples.txt"), Charsets.UTF_8);
//
//    Multiset<String> countByParser = HashMultiset.create();
//    int errorCount = 0;
//    for (String line : new HashSet<>(lines)) {
//      try {
//        when(this.request.rawMessage()).thenReturn(line);
//        BSDSyslogMessage rfc3164Message = this.parser.parse(request);
//
//        PaloAltoMessage paloAltoMessage = this.decoder.channelRead0(null, rfc3164Message);
//        countByParser.add(paloAltoMessage.type());
//        int testNumber = countByParser.count(paloAltoMessage.type());
//        final String testFileName = String.format(
//            "%s%03d.json",
//            CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, paloAltoMessage.type()),
//            testNumber);
//        File parserOutputPath = new File(this.outputRoot, paloAltoMessage.type().toLowerCase());
//        File testOutputPath = new File(parserOutputPath, testFileName);
//        TestCase testCase = new TestCase();
//        testCase.input = rfc3164Message;
//        testCase.expected = paloAltoMessage;
//        this.mapper.writeValue(testOutputPath, testCase);
//      } catch (Exception ex) {
//        log.error("Exception thrown", ex);
//        errorCount++;
//      }
//    }
//    assertEquals(0, errorCount, "errorCount should be 0.");
//  }

  public static class TestCase {
    @JsonProperty
    public BSDSyslogMessage input;
    @JsonProperty
    public PaloAltoMessage expected;
  }
}
