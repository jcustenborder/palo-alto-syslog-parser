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

import com.github.jcustenborder.netty.syslog.RFC3164Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class PaloAltoParser<T extends PaloAltoMessage> {
  private static final Logger log = LoggerFactory.getLogger(PaloAltoParser.class);

  public abstract String logType();

  public abstract T parse(RFC3164Message message, String[] fields);


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

  SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/DD hh:mm:ss");

  protected Date parseDate(String[] fields, int index) {
    log.trace("parseDate() - index='{}' fields='{}'", index, fields);
    final String input = parseString(fields, index);
    if (null == input) {
      return null;
    }
    try {
      return format.parse(input);
    } catch (ParseException e) {
      throw new IllegalStateException(e);
    }
  }

  protected Long parseLong(String[] fields, int index) {
    log.trace("parseLong() - index='{}' fields='{}'", index, fields);
    final String input = parseString(fields, index);
    if (null == input) {
      return null;
    }

    return Long.parseLong(input);
  }

  protected Integer parseInteger(String[] fields, int index) {
    log.trace("parseInteger() - index='{}' fields='{}'", index, fields);
    final String input = parseString(fields, index);
    if (null == input) {
      return null;
    }
    if (input.startsWith("0x")) {
      return Integer.decode(input);
    } else {
      return Integer.parseInt(input);
    }
  }

  protected InetAddress parseInetAddress(String[] fields, int index) {
    log.trace("parseInteger() - index='{}' fields='{}'", index, fields);
    final String input = parseString(fields, index);
    if (null == input) {
      return null;
    }
    try {
      return InetAddress.getByName(input);
    } catch (UnknownHostException e) {
      throw new IllegalStateException(e);
    }
  }
}
