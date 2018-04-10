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

import com.github.jcustenborder.netty.paloalto.ImmutableUserIdLogMessage.Builder;
import com.github.jcustenborder.netty.syslog.RFC3164Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserIdLogParser
    extends PaloAltoParser<UserIdLogMessage> {

  private final static Logger log = LoggerFactory.getLogger(UserIdLogParser.class);

  @Override
  public UserIdLogMessage parse(RFC3164Message message, String[] fields) {
    Builder builder = ImmutableUserIdLogMessage.builder();
    builder.from(message);
    log.trace("parse() - Skipping field 0: futureUse");
    log.trace("parse() - Processing field 1: receiveTime");
    builder.receiveTime(parseDate(fields, 1));
    log.trace("parse() - Processing field 2: serialNumber");
    builder.serialNumber(parseString(fields, 2));
    log.trace("parse() - Processing field 3: type");
    builder.type(parseString(fields, 3));
    log.trace("parse() - Processing field 4: threatContentType");
    builder.threatContentType(parseString(fields, 4));
    log.trace("parse() - Skipping field 5: futureUse");
    log.trace("parse() - Processing field 6: generatedTime");
    builder.generatedTime(parseDate(fields, 6));
    return builder.build();
  }

  @Override
  public String logType() {
    return "USERID";
  }

}
