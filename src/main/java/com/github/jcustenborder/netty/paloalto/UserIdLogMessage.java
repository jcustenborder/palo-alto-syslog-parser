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

import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.jcustenborder.netty.syslog.Nullable;

@JsonDeserialize(as = ImmutableUserIdLogMessage.class)
@JsonSerialize(as = ImmutableUserIdLogMessage.class)
@org.immutables.value.Value.Immutable
public interface UserIdLogMessage
    extends PaloAltoMessage
{


    /**
     * Subtype of threat log. Values include the following:
     * data - Data pattern matching a Data Filtering profile.
     * file - File type matching a File Blocking profile.
     * flood - Flood detected via a Zone Protection profile.
     * packet - Packet-based attack protection triggered by a Zone Protection profile.
     * scan - Scan detected via a Zone Protection profile.
     * spyware - Spyware detected via an Anti-Spyware profile.
     * url - URL filtering log.
     * virus - Virus detected via an Antivirus profile.
     * vulnerability - Vulnerability exploit detected via a Vulnerability Protection profile.
     * wildfire - A WildFire verdict generated when the firewall submits a file to WildFire per a WildFire Analysis profile and a verdict (malicious, phishing, grayware, or benign, depending on what you are logging) is logged in the WildFire Submissions log.
     * wildfire-virus - Virus detected via an Antivirus profile.
     * 
     * @return
     *     Subtype of threat log. Values include the following:
     *     data - Data pattern matching a Data Filtering profile.
     *     file - File type matching a File Blocking profile.
     *     flood - Flood detected via a Zone Protection profile.
     *     packet - Packet-based attack protection triggered by a Zone Protection profile.
     *     scan - Scan detected via a Zone Protection profile.
     *     spyware - Spyware detected via an Anti-Spyware profile.
     *     url - URL filtering log.
     *     virus - Virus detected via an Antivirus profile.
     *     vulnerability - Vulnerability exploit detected via a Vulnerability Protection profile.
     *     wildfire - A WildFire verdict generated when the firewall submits a file to WildFire per a WildFire Analysis profile and a verdict (malicious, phishing, grayware, or benign, depending on what you are logging) is logged in the WildFire Submissions log.
     *     wildfire-virus - Virus detected via an Antivirus profile.
     */
    @Nullable
    @Index(4)
    String threatContentType();

    /**
     * The time the log was generated on the dataplane.
     * 
     * @return
     *     The time the log was generated on the dataplane.
     */
    @Nullable
    @Index(6)
    Date generatedTime();

}
