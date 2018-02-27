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
import com.github.jcustenborder.netty.syslog.BSDSyslogMessage;
import com.github.jcustenborder.netty.syslog.RFC3164Message;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class TrafficLogParserTest extends PaloAltoParserTest<TrafficLogMessage, TrafficLogParser> {
  @Override
  protected TrafficLogParser parser() {
    return new TrafficLogParser();
  }

  @TestFactory
  public Stream<DynamicTest> parse() {
    return Arrays.stream(this.inputFiles).map(file -> dynamicTest(file.getName(), () -> {
      final TestCase testCase = this.mapper.readValue(file, TestCase.class);
      final TrafficLogMessage actual = parse(testCase.input);
      assertEquals(testCase.expected, actual);
    }));
  }

  public static class TestCase {
    @JsonProperty
    public BSDSyslogMessage input;
    @JsonProperty
    public TrafficLogMessage expected;
  }
}
