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

import com.github.jcustenborder.netty.syslog.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public abstract class PaloAltoParser<T extends PaloAltoMessage> {
  private static final Logger log = LoggerFactory.getLogger(PaloAltoParser.class);
  private final ZoneOffset zoneOffset;

  public PaloAltoParser() {
    this.formatter = new DateTimeFormatterBuilder()
        .appendPattern("yyyy/MM/dd HH:mm:ss")
        .toFormatter();
    this.zoneOffset = ZoneOffset.UTC;
  }

  public abstract String logType();

  public abstract T parse(Message message, String[] fields);


  protected String parseString(String[] fields, int index) {
    log.trace("parseString() - index='{}' fields='{}'", index, fields);
    final String result;

    if (index >= fields.length) {
      log.trace("parseString() - index {} >= {}.", index, fields.length);
      result = null;
    } else {
      result = fields[index];
    }
    log.trace("parseString() - index='{}' value='{}'", index, result);
    return result;
  }

  final DateTimeFormatter formatter;

  protected OffsetDateTime parseDate(String[] fields, int index) {
    log.trace("parseDate() - index='{}' fields='{}'", index, fields);
    final String input = parseString(fields, index);
    if (null == input) {
      return null;
    }
    return LocalDateTime.parse(input, this.formatter).atOffset(this.zoneOffset);
  }

  protected Long parseLong(String[] fields, int index) {
    log.trace("parseLong() - index='{}' fields='{}'", index, fields);
    final String input = parseString(fields, index);
    if (null == input) {
      return null;
    }
    try {
      if (input.startsWith("0x")) {
        return Long.decode(input);
      } else {
        return Long.parseLong(input);
      }
    } catch (NumberFormatException e) {
      throw new IllegalStateException(
          String.format("Exception thrown while parsing '%s' to Long. index = %s", input, index),
          e
      );
    }
  }

  protected Integer parseInteger(String[] fields, int index) {
    log.trace("parseInteger() - index='{}' fields='{}'", index, fields);
    final String input = parseString(fields, index);
    if (null == input) {
      return null;
    }
    try {
      if (input.startsWith("0x")) {
        return Integer.decode(input);
      } else {
        return Integer.parseInt(input);
      }
    } catch (NumberFormatException e) {
      throw new IllegalStateException(
          String.format("Exception thrown while parsing '%s' to Integer. index = %s", input, index),
          e
      );
    }
  }
}
