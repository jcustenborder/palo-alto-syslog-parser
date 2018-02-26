package com.github.jcustenborder.netty.paloalto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class TrafficLogPaloAltoParserTest extends PaloAltoParserTest<TrafficLogMessage, TrafficLogParser> {
  @Override
  protected PaloAltoParser<TrafficLogMessage> parser() {
    return new TrafficLogParser();
  }

  @TestFactory
  public Stream<DynamicTest> parse() {
    File[] inputFiles = this.inputRoot.listFiles((dir, name) -> name.endsWith(".json"));

    return Arrays.stream(inputFiles).map(file -> dynamicTest(file.getName(), () -> {
      final TestCase testCase = this.mapper.readValue(file, TestCase.class);
      final TrafficLogMessage actual = this.parser.parse(testCase.fields);
      assertEquals(testCase.expected, actual);
    }));
  }


  public static class TestCase {
    @JsonProperty
    public String[] fields;
    @JsonProperty
    public TrafficLogMessage expected;
  }
}
