package com.github.jcustenborder.netty.paloalto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public abstract class PaloAltoParserTest<T extends PaloAltoMessage, P extends PaloAltoParser<T>> {
  protected abstract PaloAltoParser<T> parser();

  protected ObjectMapper mapper;
  protected PaloAltoParser<T> parser;
  protected File inputRoot;

  @BeforeEach
  public void setup() {
    this.mapper = new ObjectMapper();
    this.mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    this.mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
    this.parser = parser();
    this.inputRoot = new File(
        "src/test/resources/com/github/jcustenborder/netty/syslog",
        this.parser.logType().toLowerCase()
    );
  }
}
