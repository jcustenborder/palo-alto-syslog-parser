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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.EventExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public abstract class PaloAltoParserTest<M extends PaloAltoMessage, P extends PaloAltoParser<M>, T extends BaseTestCase<M>> {
  protected abstract P parser();

  protected abstract Class<M> messageClass();

  protected abstract Class<T> testCaseClass();

  protected abstract void assertMessage(M expected, M actual);

  protected ObjectMapper mapper;
  protected PaloAltoMessageHandler decoder;
  protected File inputRoot;
  protected File[] inputFiles;

  protected Class<T> testCaseClass;
  protected Class<M> messageClass;

  @Mock
  protected ChannelHandlerContext context;
  @Mock
  protected EventExecutor eventExecutor;

  @BeforeEach
  public void setup() {
    this.testCaseClass = testCaseClass();
    this.messageClass = messageClass();
    this.mapper = new ObjectMapper();
    this.mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    this.mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
    this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    this.mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
    this.mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);

    this.mapper.registerModule(new JavaTimeModule());
    P parser = parser();
    this.decoder = new PaloAltoMessageHandler(parser);

    this.inputRoot = new File(
        "src/test/resources/com/github/jcustenborder/netty/syslog",
        parser.logType().toLowerCase()
    );
    this.inputFiles = this.inputRoot.listFiles((dir, name) -> name.endsWith(".json"));
  }


  @TestFactory
  public Stream<DynamicTest> parse() {
    return Arrays.stream(this.inputFiles).map(file -> dynamicTest(file.getName(), () -> {
      final T testCase = this.mapper.readValue(file, this.testCaseClass);
      when(context.executor()).thenReturn(eventExecutor);

      doAnswer(invocationOnMock -> {
        Runnable runnable = invocationOnMock.getArgument(0);
        runnable.run();
        return null;
      }).when(eventExecutor).execute(any(Runnable.class));

      when(context.fireChannelRead(any(this.messageClass))).thenAnswer((Answer<ChannelHandlerContext>) invocationOnMock -> {
        M actual = invocationOnMock.getArgument(0);
        assertMessage(testCase.expected, actual);
        return context;
      });

      this.decoder.channelRead0(context, testCase.input);
    }));
  }

}
