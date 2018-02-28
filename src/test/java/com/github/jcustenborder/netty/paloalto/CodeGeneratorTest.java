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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.jcustenborder.netty.syslog.Nullable;
import com.github.jcustenborder.netty.syslog.RFC3164Message;
import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.Files;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;
import org.immutables.value.Value;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class CodeGeneratorTest {
  private static final Logger log = LoggerFactory.getLogger(CodeGeneratorTest.class);

  static final String FUTURE_USE = "FUTURE_USE";
  static final Set<String> SKIP_FIELDS = ImmutableSet.of(FUTURE_USE);


  @TestFactory
  public Stream<DynamicTest> unclean() throws IOException {
    File rootPath = new File("src/test/resources/unclean");
    File outputPath = new File("src/test/resources/data/");
    File[] inputFiles = rootPath.listFiles((dir, name) -> name.endsWith(".json"));

    Map<String, Class<?>> fieldTypeLookup = new LinkedHashMap<>();
    fieldTypeLookup.put("receiveTime", Date.class);
    fieldTypeLookup.put("generatedTime", Date.class);
    fieldTypeLookup.put("sourceIp", InetAddress.class);
    fieldTypeLookup.put("destinationIp", InetAddress.class);
    fieldTypeLookup.put("natSourceIp", InetAddress.class);
    fieldTypeLookup.put("natDestinationIp", InetAddress.class);
    fieldTypeLookup.put("sessionId", Long.class);
    fieldTypeLookup.put("repeatCount", Long.class);
    fieldTypeLookup.put("sourcePort", Integer.class);
    fieldTypeLookup.put("destinationPort", Integer.class);
    fieldTypeLookup.put("natSourcePort", Integer.class);
    fieldTypeLookup.put("natDestinationPort", Integer.class);
    fieldTypeLookup.put("bytes", Long.class);
    fieldTypeLookup.put("bytesSent", Long.class);
    fieldTypeLookup.put("bytesReceived", Long.class);
    fieldTypeLookup.put("packets", Long.class);
    fieldTypeLookup.put("startTime", Date.class);
    fieldTypeLookup.put("parentStartTime", Date.class);
    fieldTypeLookup.put("elapsedTime", Long.class);
    fieldTypeLookup.put("sequenceNumber", Long.class);
    fieldTypeLookup.put("packetsSent", Long.class);
    fieldTypeLookup.put("packetsReceived", Long.class);
    fieldTypeLookup.put("actionFlags", String.class);
    fieldTypeLookup.put("flags", Long.class);

    Map<String, String> methodReplace = new HashMap<>();
    methodReplace.put("contentThreatType", "subType");


    return Arrays.stream(inputFiles).map(f -> dynamicTest(f.getName(), () -> {
      RawInput rawInput = this.mapper.readValue(f, RawInput.class);
      DataInterface dataInterface = new DataInterface();
      String fileName = Files.getNameWithoutExtension(f.getName()).replaceAll("[\\-]+", "_");
      dataInterface.name = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, fileName) + "Log";
      dataInterface.logType = Files.getNameWithoutExtension(f.getName());
      int index = 0;
      for (String fieldName : rawInput.format) {
        if (!rawInput.fields.containsKey(fieldName) && !FUTURE_USE.equals(fieldName)) {
          log.warn("Could not find field '{}'", fieldName);
        }

        DataInterface.Field field = new DataInterface.Field();
        field.name = fieldName;
        field.doc = rawInput.fields.getOrDefault(fieldName, "");
        field.doc = field.doc.replaceAll("\\r\\n", "\n");
        field.methodName = field.name.replaceAll("[/\\s-]+", "_").toUpperCase();
        field.methodName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.methodName);
        field.methodName = methodReplace.getOrDefault(field.methodName, field.methodName);
        field.index = index;
        field.skip = SKIP_FIELDS.contains(fieldName);
        field.type = fieldTypeLookup.getOrDefault(field.methodName, String.class);
        dataInterface.fields.add(field);
        index++;
      }
      mapper.writeValue(new File(outputPath, f.getName()), dataInterface);
    }));
  }

  static ObjectMapper mapper;
  static JCodeModel model;
  static JClass parserClass;
  static JClass loggerClass;
  static JClass loggerFactoryClass;
  static JClass messageClass;
  static File[] inputFiles;

  @BeforeAll
  static void beforeAll() {
    mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    model = new JCodeModel();
    parserClass = (JClass) model._ref(PaloAltoParser.class);
    loggerClass = (JClass) model._ref(Logger.class);
    loggerFactoryClass = (JClass) model._ref(LoggerFactory.class);
    messageClass = (JClass) model._ref(RFC3164Message.class);

    inputFiles = new File("src/test/resources/data").listFiles((dir, name) -> name.endsWith(".json"));
  }

  @AfterAll
  static void afterAll() throws IOException {
    model.build(new File("src/main/java"));
  }

  @Test
  public void generateReadMe() throws IOException {
    final CSVParser parser = new CSVParserBuilder()
        .withSeparator('|')
        .build();

    Path documentRoot = Paths.get("https://docs.oracle.com/javase/8/docs/api/");


    try (BufferedWriter builder = Files.newWriter(new File("target/README.md"), Charsets.UTF_8)) {
      Arrays.stream(inputFiles).forEach(f -> {
        try {
          DataInterface dataInterface = mapper.readValue(f, DataInterface.class);
          builder.append("# ");
          builder.append(dataInterface.messageName().substring(dataInterface.messageName().lastIndexOf('.') + 1));
          builder.append('\n');
          builder.append('\n');


          String[] headers = new String[]{"Name", "Type"};
          String headerRow = parser.parseToLine(headers);
          builder.append('|');
          builder.append(headerRow);
          builder.append("|\n");

          builder.append('|');


          builder.append(parser.parseToLine(Arrays.stream(headers).map(h -> Strings.repeat("-", h.length())).toArray(String[]::new)));

          builder.append('|');
          builder.append('\n');

          dataInterface.fields.stream().filter(field -> !field.skip).forEach(field -> {
            try {
              builder.append('|');

              builder.append(
                  parser.parseToLine(new String[]{
                      field.name,
                      String.format("[%s](%s)", field.type.getName(), documentRoot.resolve(field.type.getName().replace('.', '/') + ".html"))
                  })
              );
              builder.append('|');

              builder.append('\n');
            } catch (IOException ex) {

            }
          });
          builder.append('\n');
        } catch (IOException e) {
          log.error("Exception thrown", e);
        }
      });
    }
  }

  @TestFactory
  public Stream<DynamicTest> generate() throws IOException, JClassAlreadyExistsException {
    return Arrays.stream(inputFiles).map(f -> dynamicTest(f.getName(), () -> {
      DataInterface dataInterface = mapper.readValue(f, DataInterface.class);
      JDefinedClass messageInterface = addInterface(dataInterface);
      addParser(dataInterface, messageInterface);
    }));
  }

  private void addParser(DataInterface dataInterface, JDefinedClass messageInterface) throws JClassAlreadyExistsException {
    JDefinedClass jDefinedClass = model._class(JMod.PUBLIC, dataInterface.parserName(), ClassType.CLASS);
    jDefinedClass._extends(parserClass.narrow(messageInterface));
    final JFieldVar logVar = jDefinedClass.field(
        JMod.STATIC | JMod.FINAL | JMod.PRIVATE,
        loggerClass,
        "log",
        loggerFactoryClass.staticInvoke("getLogger").arg(jDefinedClass.dotclass())
    );

    final JMethod parseMethod = jDefinedClass.method(JMod.PUBLIC, messageInterface, "parse");
    final JVar messageVar = parseMethod.param(messageClass, "message");
    final JVar fieldsVar = parseMethod.param(String[].class, "fields");
    parseMethod.annotate(Override.class);
    final JClass builderClass = model.ref(dataInterface.immutableBuilderClassName());
    final JClass immutableMessageClass = model.ref(dataInterface.immutableClassName());
    final JVar builderVar = parseMethod.body().decl(
        builderClass, "builder", immutableMessageClass.staticInvoke("builder")
    );
    parseMethod.body().invoke(builderVar, "from").arg(messageVar);
    for (DataInterface.Field field : dataInterface.fields) {
      if (!field.skip) {
        parseMethod.body().invoke(logVar, "trace").arg(
            String.format("parse() - Processing field %s: %s", field.index, field.methodName)
        );
        String methodName = String.format("parse%s", field.type.getSimpleName());
        parseMethod.body().invoke(builderVar, field.methodName).arg(
            JExpr.invoke(methodName)
                .arg(fieldsVar)
                .arg(JExpr.lit(field.index))
        );

      } else {
        parseMethod.body().invoke(logVar, "trace").arg(
            String.format("parse() - Skipping field %s: %s", field.index, field.methodName)
        );
      }
    }


    parseMethod.body()._return(builderVar.invoke("build"));

    final JMethod logTypeMethod = jDefinedClass.method(JMod.PUBLIC, String.class, "logType");
    logTypeMethod.annotate(Override.class);
    logTypeMethod.body()._return(JExpr.lit(dataInterface.logType.replaceAll("\\-", "").toUpperCase()));
  }

  private JDefinedClass addInterface(DataInterface dataInterface) throws JClassAlreadyExistsException {
    JDefinedClass jDefinedClass = model._class(JMod.PUBLIC, dataInterface.messageName(), ClassType.INTERFACE);
    JClass immutableClass = model.ref(dataInterface.immutableClassName());

    jDefinedClass.annotate(JsonDeserialize.class).param("as", immutableClass.dotclass());
    jDefinedClass.annotate(JsonSerialize.class).param("as", immutableClass.dotclass());
    jDefinedClass.annotate(Value.Immutable.class);
    jDefinedClass._implements(PaloAltoMessage.class);


    for (DataInterface.Field field : dataInterface.fields) {
      if (!field.shouldDefine()) {
        continue;
      }
      JMethod method = jDefinedClass.method(JMod.NONE, field.type, field.methodName);
      if(!Strings.isNullOrEmpty(field.doc)) {
        method.javadoc().append(field.doc.trim());
        method.javadoc().addReturn().add(field.doc.trim());
      }
      method.annotate(Nullable.class);
      method.annotate(Index.class).param("value", field.index);
    }

    return jDefinedClass;
  }
}
