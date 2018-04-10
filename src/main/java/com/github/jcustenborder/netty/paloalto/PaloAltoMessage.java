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

import com.github.jcustenborder.netty.syslog.Nullable;
import com.github.jcustenborder.netty.syslog.RFC3164Message;

import java.time.OffsetDateTime;

public interface PaloAltoMessage extends RFC3164Message {
  /**
   * Time the log was received at the management plane.
   * @return Time the log was received at the management plane.
   */
  @Nullable
  @Index(1)
  OffsetDateTime receiveTime();

  /**
   * Serial number of the firewall that generated the log.
   * @return Serial number of the firewall that generated the log.
   */
  @Index(2)
  String serialNumber();

  /**
   * Specifies type of log; values are traffic, threat, config, system and hip-match.
   * @return Specifies type of log; values are traffic, threat, config, system and hip-match.
   */
  @Nullable
  @Index(3)
  String type();
}
