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
import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaloAltoMessageDecoderTest {
  private static final Logger log = LoggerFactory.getLogger(PaloAltoMessageDecoderTest.class);
  PaloAltoMessageDecoder decoder;
  RFC3164MessageParser parser;
  SyslogRequest request;
  File outputRoot = new File("src/test/resources/com/github/jcustenborder/netty/syslog");
  ObjectMapper mapper;

  @BeforeEach
  public void before() throws UnknownHostException {
    this.parser = new RFC3164MessageParser();
    this.request = mock(SyslogRequest.class);
    when(this.request.receivedDate()).thenReturn(new Date(1519755720000L));
    when(this.request.remoteAddress()).thenReturn(InetAddress.getLocalHost());
    this.decoder = new PaloAltoMessageDecoder();
    this.mapper = new ObjectMapper();
    this.mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    this.mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
  }

  @Test
  public void noParsers() {
    assertThrows(IllegalStateException.class, () -> {
      new PaloAltoMessageDecoder(Collections.emptyList());
    });
  }

  @Disabled
  @Test
  public void generateTestData() throws IOException {
    int index = 0;
    List<String> lines = Files.readLines(new File("src/test/resources/samples.txt"), Charsets.UTF_8);

    Multiset<String> countByParser = HashMultiset.create();
    int errorCount = 0;
    for (String line : new HashSet<>(lines)) {
      try {
        when(this.request.rawMessage()).thenReturn(line);
        List<Object> output = new ArrayList<>();
        this.parser.parse(request, output);
        assertFalse(output.isEmpty(), "output should not be false.");
        assertTrue(output.get(0) instanceof BSDSyslogMessage, "output should contain RFC3164Message.");
        BSDSyslogMessage rfc3164Message = (BSDSyslogMessage) output.get(0);
        output.clear();
        this.decoder.decode(null, rfc3164Message, output);
        Preconditions.checkState(!output.isEmpty(), "output is empty.");
        Preconditions.checkState(output.get(0) instanceof PaloAltoMessage, "output should contain PaloAltoMessage.");
        PaloAltoMessage paloAltoMessage = (PaloAltoMessage) output.get(0);
        countByParser.add(paloAltoMessage.type());
        int testNumber = countByParser.count(paloAltoMessage.type());
        final String testFileName = String.format(
            "%s%03d.json",
            CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, paloAltoMessage.type()),
            testNumber);
        File parserOutputPath = new File(this.outputRoot, paloAltoMessage.type().toLowerCase());
        File testOutputPath = new File(parserOutputPath, testFileName);
        TestCase testCase = new TestCase();
        testCase.input = rfc3164Message;
        testCase.expected = paloAltoMessage;
        this.mapper.writeValue(testOutputPath, testCase);
      } catch (Exception ex) {
        log.error("Exception thrown", ex);
        errorCount++;
      }
    }
    assertEquals(0, errorCount, "errorCount should be 0.");
  }

  public static class TestCase {
    @JsonProperty
    public BSDSyslogMessage input;
    @JsonProperty
    public PaloAltoMessage expected;
  }
}
