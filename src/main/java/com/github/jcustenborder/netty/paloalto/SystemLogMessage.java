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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.jcustenborder.netty.syslog.Nullable;

import java.time.OffsetDateTime;

@JsonDeserialize(as = ImmutableSystemLogMessage.class)
@JsonSerialize(as = ImmutableSystemLogMessage.class)
@org.immutables.value.Value.Immutable
public interface SystemLogMessage
    extends PaloAltoMessage {


  /**
   * Subtype of the system log; refers to the system daemon generating the log; values are crypto, dhcp, dnsproxy, dos, general, global-protect, ha, hw, nat, ntpd, pbf, port, pppoe, ras, routing, satd, sslmgr, sslvpn, userid, url-filtering, vpn.
   *
   * @return
   *     Subtype of the system log; refers to the system daemon generating the log; values are crypto, dhcp, dnsproxy, dos, general, global-protect, ha, hw, nat, ntpd, pbf, port, pppoe, ras, routing, satd, sslmgr, sslvpn, userid, url-filtering, vpn.
   */
  @Nullable
  @Index(4)
  String subType();

  /**
   * Time the log was generated on the dataplane.
   *
   * @return
   *     Time the log was generated on the dataplane.
   */
  @Nullable
  @Index(6)
  OffsetDateTime generatedTime();

  /**
   * Virtual System associated with the configuration log
   *
   * @return
   *     Virtual System associated with the configuration log
   */
  @Nullable
  @Index(7)
  String virtualSystem();

  /**
   * String showing the name of the event
   *
   * @return
   *     String showing the name of the event
   */
  @Nullable
  @Index(8)
  String eventId();

  /**
   * Name of the object associated with the system event
   *
   * @return
   *     Name of the object associated with the system event
   */
  @Nullable
  @Index(9)
  String object();

  /**
   * This field is valid only when the value of the Subtype field is general. It provides additional information about the sub-system generating the log; values are general, management, auth, ha, upgrade, chassis
   *
   * @return
   *     This field is valid only when the value of the Subtype field is general. It provides additional information about the sub-system generating the log; values are general, management, auth, ha, upgrade, chassis
   */
  @Nullable
  @Index(12)
  String module();

  /**
   * Severity associated with the event; values are informational, low, medium, high, critical
   *
   * @return
   *     Severity associated with the event; values are informational, low, medium, high, critical
   */
  @Nullable
  @Index(13)
  String severity();

  /**
   * Detailed description of the event, up to a maximum of 512 bytes
   *
   * @return
   *     Detailed description of the event, up to a maximum of 512 bytes
   */
  @Nullable
  @Index(14)
  String description();

  /**
   * A 64bit log entry identifier incremented sequentially; each log type has a unique number space.
   *
   * @return
   *     A 64bit log entry identifier incremented sequentially; each log type has a unique number space.
   */
  @Nullable
  @Index(15)
  Long sequenceNumber();

  /**
   * A bit field indicating if the log was forwarded to Panorama.
   *
   * @return
   *     A bit field indicating if the log was forwarded to Panorama.
   */
  @Nullable
  @Index(16)
  String actionFlags();

  /**
   * A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   * If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   *
   * @return
   *     A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   *     If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   */
  @Nullable
  @Index(17)
  String deviceGroupHierarchyLevel1();

  /**
   * A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   * If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   *
   * @return
   *     A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   *     If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   */
  @Nullable
  @Index(18)
  String deviceGroupHierarchyLevel2();

  /**
   * A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   * If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   *
   * @return
   *     A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   *     If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   */
  @Nullable
  @Index(19)
  String deviceGroupHierarchyLevel3();

  /**
   * A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   * If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   *
   * @return
   *     A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   *     If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   */
  @Nullable
  @Index(20)
  String deviceGroupHierarchyLevel4();

  /**
   * The name of the virtual system associated with the session; only valid on firewalls enabled for multiple virtual systems.
   *
   * @return
   *     The name of the virtual system associated with the session; only valid on firewalls enabled for multiple virtual systems.
   */
  @Nullable
  @Index(21)
  String virtualSystemName();

  /**
   * The hostname of the firewall on which the session was logged.
   *
   * @return
   *     The hostname of the firewall on which the session was logged.
   */
  @Nullable
  @Index(22)
  String deviceName();

}
