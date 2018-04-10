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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.jcustenborder.netty.syslog.Nullable;

import java.time.OffsetDateTime;

@JsonDeserialize(as = ImmutableTrafficLogMessage.class)
@JsonSerialize(as = ImmutableTrafficLogMessage.class)
@org.immutables.value.Value.Immutable
public interface TrafficLogMessage
    extends PaloAltoMessage {


  /**
   * Subtype of traffic log; values are start, end, drop, and deny
   * Start—session started
   * End—session ended
   * Drop—session dropped before the application is identified and there is no rule that allows the session.
   * Deny—session dropped after the application is identified and there is a rule to block or no rule that allows the session.
   *
   * @return
   *     Subtype of traffic log; values are start, end, drop, and deny
   *     Start—session started
   *     End—session ended
   *     Drop—session dropped before the application is identified and there is no rule that allows the session.
   *     Deny—session dropped after the application is identified and there is a rule to block or no rule that allows the session.
   */
  @Nullable
  @Index(4)
  String threatContentType();

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
   * Original session source IP address.
   *
   * @return
   *     Original session source IP address.
   */
  @Nullable
  @Index(7)
  String sourceIp();

  /**
   * Original session destination IP address.
   *
   * @return
   *     Original session destination IP address.
   */
  @Nullable
  @Index(8)
  String destinationIp();

  /**
   * If Source NAT performed, the post-NAT Source IP address.
   *
   * @return
   *     If Source NAT performed, the post-NAT Source IP address.
   */
  @Nullable
  @Index(9)
  String natSourceIp();

  /**
   * If Destination NAT performed, the post-NAT Destination IP address.
   *
   * @return
   *     If Destination NAT performed, the post-NAT Destination IP address.
   */
  @Nullable
  @Index(10)
  String natDestinationIp();

  /**
   * Name of the rule that the session matched.
   *
   * @return
   *     Name of the rule that the session matched.
   */
  @Nullable
  @Index(11)
  String ruleName();

  /**
   * Username of the user who initiated the session.
   *
   * @return
   *     Username of the user who initiated the session.
   */
  @Nullable
  @Index(12)
  String sourceUser();

  /**
   * Username of the user to which the session was destined.
   *
   * @return
   *     Username of the user to which the session was destined.
   */
  @Nullable
  @Index(13)
  String destinationUser();

  /**
   * Application associated with the session.
   *
   * @return
   *     Application associated with the session.
   */
  @Nullable
  @Index(14)
  String application();

  /**
   * Virtual System associated with the session.
   *
   * @return
   *     Virtual System associated with the session.
   */
  @Nullable
  @Index(15)
  String virtualSystem();

  /**
   * Zone the session was sourced from.
   *
   * @return
   *     Zone the session was sourced from.
   */
  @Nullable
  @Index(16)
  String sourceZone();

  /**
   * Zone the session was destined to.
   *
   * @return
   *     Zone the session was destined to.
   */
  @Nullable
  @Index(17)
  String destinationZone();

  /**
   * Interface that the session was sourced from.
   *
   * @return
   *     Interface that the session was sourced from.
   */
  @Nullable
  @Index(18)
  String inboundInterface();

  /**
   * Interface that the session was destined to.
   *
   * @return
   *     Interface that the session was destined to.
   */
  @Nullable
  @Index(19)
  String outboundInterface();

  /**
   * Log Forwarding Profile that was applied to the session.
   *
   * @return
   *     Log Forwarding Profile that was applied to the session.
   */
  @Nullable
  @Index(20)
  String logAction();

  /**
   * An internal numerical identifier applied to each session.
   *
   * @return
   *     An internal numerical identifier applied to each session.
   */
  @Nullable
  @Index(22)
  Long sessionId();

  /**
   * Number of sessions with same Source IP, Destination IP, Application, and Subtype seen within 5 seconds; used for ICMP only.
   *
   * @return
   *     Number of sessions with same Source IP, Destination IP, Application, and Subtype seen within 5 seconds; used for ICMP only.
   */
  @Nullable
  @Index(23)
  Long repeatCount();

  /**
   * Source port utilized by the session.
   *
   * @return
   *     Source port utilized by the session.
   */
  @Nullable
  @Index(24)
  Integer sourcePort();

  /**
   * Destination port utilized by the session.
   *
   * @return
   *     Destination port utilized by the session.
   */
  @Nullable
  @Index(25)
  Integer destinationPort();

  /**
   * Post-NAT source port.
   *
   * @return
   *     Post-NAT source port.
   */
  @Nullable
  @Index(26)
  Integer natSourcePort();

  /**
   * Post-NAT destination port.
   *
   * @return
   *     Post-NAT destination port.
   */
  @Nullable
  @Index(27)
  Integer natDestinationPort();

  /**
   *  32-bit field that provides details on session; this field can be decoded by AND-ing the values with the logged value:
   *  0x80000000 —session has a packet capture (PCAP)
   *  0x02000000 —IPv6 session
   *  0x01000000 —SSL session was decrypted (SSL Proxy)
   *  0x00800000 —session was denied via URL filtering
   *  0x00400000 —session has a NAT translation performed (NAT)
   *  0x00200000 —user information for the session was captured through Captive Portal
   *  0x00080000 —X-Forwarded-For value from a proxy is in the source user field
   *  0x00040000 —log corresponds to a transaction within a http proxy session (Proxy Transaction)
   *  0x00008000 —session is a container page access (Container Page)
   *  0x00002000 —session has a temporary match on a rule for implicit application dependency handling. Available in PAN-OS 5.0.0 and above.
   *  0x00000800 —symmetric return was used to forward traffic for this session
   *
   * @return
   *      32-bit field that provides details on session; this field can be decoded by AND-ing the values with the logged value:
   *      0x80000000 —session has a packet capture (PCAP)
   *      0x02000000 —IPv6 session
   *      0x01000000 —SSL session was decrypted (SSL Proxy)
   *      0x00800000 —session was denied via URL filtering
   *      0x00400000 —session has a NAT translation performed (NAT)
   *      0x00200000 —user information for the session was captured through Captive Portal
   *      0x00080000 —X-Forwarded-For value from a proxy is in the source user field
   *      0x00040000 —log corresponds to a transaction within a http proxy session (Proxy Transaction)
   *      0x00008000 —session is a container page access (Container Page)
   *      0x00002000 —session has a temporary match on a rule for implicit application dependency handling. Available in PAN-OS 5.0.0 and above.
   *      0x00000800 —symmetric return was used to forward traffic for this session
   */
  @Nullable
  @Index(28)
  Long flags();

  /**
   * IP protocol associated with the session.
   *
   * @return
   *     IP protocol associated with the session.
   */
  @Nullable
  @Index(29)
  String protocol();

  /**
   * Action taken for the session; possible values are:
   * Allow—session was allowed by policy
   * Deny—session was denied by policy
   * Drop—session was dropped silently
   * Drop ICMP—session was silently dropped with an ICMP unreachable message to the host or application
   * Reset both—session was terminated and a TCP reset is sent to both the sides of the connection
   * Reset client—session was terminated and a TCP reset is sent to the client
   * Reset server—session was terminated and a TCP reset is sent to the server
   *
   * @return
   *     Action taken for the session; possible values are:
   *     Allow—session was allowed by policy
   *     Deny—session was denied by policy
   *     Drop—session was dropped silently
   *     Drop ICMP—session was silently dropped with an ICMP unreachable message to the host or application
   *     Reset both—session was terminated and a TCP reset is sent to both the sides of the connection
   *     Reset client—session was terminated and a TCP reset is sent to the client
   *     Reset server—session was terminated and a TCP reset is sent to the server
   */
  @Nullable
  @Index(30)
  String action();

  /**
   * Number of total bytes (transmit and receive) for the session.
   *
   * @return
   *     Number of total bytes (transmit and receive) for the session.
   */
  @Nullable
  @Index(31)
  Long bytes();

  /**
   * Number of bytes in the client-to-server direction of the session.
   * Available on all models except the PA-4000 Series.
   *
   * @return
   *     Number of bytes in the client-to-server direction of the session.
   *     Available on all models except the PA-4000 Series.
   */
  @Nullable
  @Index(32)
  Long bytesSent();

  /**
   * Number of bytes in the server-to-client direction of the session.
   * Available on all models except the PA-4000 Series.
   *
   * @return
   *     Number of bytes in the server-to-client direction of the session.
   *     Available on all models except the PA-4000 Series.
   */
  @Nullable
  @Index(33)
  Long bytesReceived();

  /**
   * Number of total packets (transmit and receive) for the session.
   *
   * @return
   *     Number of total packets (transmit and receive) for the session.
   */
  @Nullable
  @Index(34)
  Long packets();

  /**
   * Time of session start.
   *
   * @return
   *     Time of session start.
   */
  @Nullable
  @Index(35)
  OffsetDateTime startTime();

  /**
   * Elapsed time of the session.
   *
   * @return
   *     Elapsed time of the session.
   */
  @Nullable
  @Index(36)
  Long elapsedTime();

  /**
   * URL category associated with the session (if applicable).
   *
   * @return
   *     URL category associated with the session (if applicable).
   */
  @Nullable
  @Index(37)
  String category();

  /**
   * A 64-bit log entry identifier incremented sequentially; each log type has a unique number space.
   *
   * @return
   *     A 64-bit log entry identifier incremented sequentially; each log type has a unique number space.
   */
  @Nullable
  @Index(39)
  Long sequenceNumber();

  /**
   * A bit field indicating if the log was forwarded to Panorama.
   *
   * @return
   *     A bit field indicating if the log was forwarded to Panorama.
   */
  @Nullable
  @Index(40)
  String actionFlags();

  /**
   * Source country or Internal region for private addresses; maximum length is 32 bytes.
   *
   * @return
   *     Source country or Internal region for private addresses; maximum length is 32 bytes.
   */
  @Nullable
  @Index(41)
  String sourceLocation();

  /**
   * Destination country or Internal region for private addresses. Maximum length is 32 bytes.
   *
   * @return
   *     Destination country or Internal region for private addresses. Maximum length is 32 bytes.
   */
  @Nullable
  @Index(42)
  String destinationLocation();

  /**
   * Number of client-to-server packets for the session.
   * Available on all models except the PA-4000 Series.
   *
   * @return
   *     Number of client-to-server packets for the session.
   *     Available on all models except the PA-4000 Series.
   */
  @Nullable
  @Index(44)
  Long packetsSent();

  /**
   * Number of server-to-client packets for the session.
   * Available on all models except the PA-4000 Series.
   *
   * @return
   *     Number of server-to-client packets for the session.
   *     Available on all models except the PA-4000 Series.
   */
  @Nullable
  @Index(45)
  Long packetsReceived();

  /**
   * The reason a session terminated. If the termination had multiple causes, this field displays only the highest priority reason. The possible session end reason values are as follows, in order of priority (where the first is highest):
   * threat—The firewall detected a threat associated with a reset, drop, or block (IP address) action.
   * policy-deny—The session matched a security rule with a deny or drop action.
   * decrypt-cert-validation—The session terminated because you configured the firewall to block SSL forward proxy decryption or SSL inbound inspection when the session uses client authentication or when the session uses a server certificate with any of the following conditions: expired, untrusted issuer, unknown status, or status verification time-out. This session end reason also displays when the server certificate produces a fatal error alert of type bad_certificate, unsupported_certificate, certificate_revoked, access_denied, or no_certificate_RESERVED (SSLv3 only).
   * decrypt-unsupport-param—The session terminated because you configured the firewall to block SSL forward proxy decryption or SSL inbound inspection when the session uses an unsupported protocol version, cipher, or SSH algorithm. This session end reason is displays when the session produces a fatal error alert of type unsupported_extension, unexpected_message, or handshake_failure.
   * decrypt-error—The session terminated because you configured the firewall to block SSL forward proxy decryption or SSL inbound inspection when firewall resources or the hardware security module (HSM) were unavailable. This session end reason is also displayed when you configured the firewall to block SSL traffic that has SSH errors or that produced any fatal error alert other than those listed for the decrypt-cert-validation and decrypt-unsupport-param end reasons.
   * tcp-rst-from-client—The client sent a TCP reset to the server.
   * tcp-rst-from-server—The server sent a TCP reset to the client.
   * resources-unavailable—The session dropped because of a system resource limitation. For example, the session could have exceeded the number of out-of-order packets allowed per flow or the global out-of-order packet queue.
   * tcp-fin—One host or both hosts in the connection sent a TCP FIN message to close the session.
   * tcp-reuse—A session is reused and the firewall closes the previous session.
   * decoder—The decoder detects a new connection within the protocol (such as HTTP-Proxy) and ends the previous connection.
   * aged-out—The session aged out.
   * unknown—This value applies in the following situations:
   * Session terminations that the preceding reasons do not cover (for example, a clear session all command).
   * For logs generated in a PAN-OS release that does not support the session end reason field (releases older than PAN-OS 6.1), the value will be unknown after an upgrade to the current PAN-OS release or after the logs are loaded onto the firewall.
   * In Panorama, logs received from firewalls for which the PAN-OS version does not support session end reasons will have a value of unknown.
   * n/a—This value applies when the traffic log type is not end.
   *
   * @return
   *     The reason a session terminated. If the termination had multiple causes, this field displays only the highest priority reason. The possible session end reason values are as follows, in order of priority (where the first is highest):
   *     threat—The firewall detected a threat associated with a reset, drop, or block (IP address) action.
   *     policy-deny—The session matched a security rule with a deny or drop action.
   *     decrypt-cert-validation—The session terminated because you configured the firewall to block SSL forward proxy decryption or SSL inbound inspection when the session uses client authentication or when the session uses a server certificate with any of the following conditions: expired, untrusted issuer, unknown status, or status verification time-out. This session end reason also displays when the server certificate produces a fatal error alert of type bad_certificate, unsupported_certificate, certificate_revoked, access_denied, or no_certificate_RESERVED (SSLv3 only).
   *     decrypt-unsupport-param—The session terminated because you configured the firewall to block SSL forward proxy decryption or SSL inbound inspection when the session uses an unsupported protocol version, cipher, or SSH algorithm. This session end reason is displays when the session produces a fatal error alert of type unsupported_extension, unexpected_message, or handshake_failure.
   *     decrypt-error—The session terminated because you configured the firewall to block SSL forward proxy decryption or SSL inbound inspection when firewall resources or the hardware security module (HSM) were unavailable. This session end reason is also displayed when you configured the firewall to block SSL traffic that has SSH errors or that produced any fatal error alert other than those listed for the decrypt-cert-validation and decrypt-unsupport-param end reasons.
   *     tcp-rst-from-client—The client sent a TCP reset to the server.
   *     tcp-rst-from-server—The server sent a TCP reset to the client.
   *     resources-unavailable—The session dropped because of a system resource limitation. For example, the session could have exceeded the number of out-of-order packets allowed per flow or the global out-of-order packet queue.
   *     tcp-fin—One host or both hosts in the connection sent a TCP FIN message to close the session.
   *     tcp-reuse—A session is reused and the firewall closes the previous session.
   *     decoder—The decoder detects a new connection within the protocol (such as HTTP-Proxy) and ends the previous connection.
   *     aged-out—The session aged out.
   *     unknown—This value applies in the following situations:
   *     Session terminations that the preceding reasons do not cover (for example, a clear session all command).
   *     For logs generated in a PAN-OS release that does not support the session end reason field (releases older than PAN-OS 6.1), the value will be unknown after an upgrade to the current PAN-OS release or after the logs are loaded onto the firewall.
   *     In Panorama, logs received from firewalls for which the PAN-OS version does not support session end reasons will have a value of unknown.
   *     n/a—This value applies when the traffic log type is not end.
   */
  @Nullable
  @Index(46)
  String sessionEndReason();

  /**
   * A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   * If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   * CLI command in configure mode: show readonly dg-meta-data
   *
   * @return
   *     A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   *     If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   *     CLI command in configure mode: show readonly dg-meta-data
   */
  @Nullable
  @Index(47)
  String deviceGroupHierarchyLevel1();

  /**
   * A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   * If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   * CLI command in configure mode: show readonly dg-meta-data
   *
   * @return
   *     A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   *     If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   *     CLI command in configure mode: show readonly dg-meta-data
   */
  @Nullable
  @Index(48)
  String deviceGroupHierarchyLevel2();

  /**
   * A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   * If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   * CLI command in configure mode: show readonly dg-meta-data
   *
   * @return
   *     A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   *     If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   *     CLI command in configure mode: show readonly dg-meta-data
   */
  @Nullable
  @Index(49)
  String deviceGroupHierarchyLevel3();

  /**
   * A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   * If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   * CLI command in configure mode: show readonly dg-meta-data
   *
   * @return
   *     A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
   *     If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
   *     CLI command in configure mode: show readonly dg-meta-data
   */
  @Nullable
  @Index(50)
  String deviceGroupHierarchyLevel4();

  /**
   * The name of the virtual system associated with the session; only valid on firewalls enabled for multiple virtual systems.
   *
   * @return
   *     The name of the virtual system associated with the session; only valid on firewalls enabled for multiple virtual systems.
   */
  @Nullable
  @Index(51)
  String virtualSystemName();

  /**
   * The hostname of the firewall on which the session was logged.
   *
   * @return
   *     The hostname of the firewall on which the session was logged.
   */
  @Nullable
  @Index(52)
  String deviceName();

  /**
   * Specifies whether the action taken to allow or block an application was defined in the application or in policy. The actions can be allow, deny, drop, reset- server, reset-client or reset-both for the session.
   *
   * @return
   *     Specifies whether the action taken to allow or block an application was defined in the application or in policy. The actions can be allow, deny, drop, reset- server, reset-client or reset-both for the session.
   */
  @Nullable
  @Index(53)
  String actionSource();

  /**
   * Identifies the source universal unique identifier for a guest virtual machine in the VMware NSX environment.
   *
   * @return
   *     Identifies the source universal unique identifier for a guest virtual machine in the VMware NSX environment.
   */
  @Nullable
  @Index(54)
  String sourceVmUuid();

  /**
   * Identifies the destination universal unique identifier for a guest virtual machine in the VMware NSX environment.
   *
   * @return
   *     Identifies the destination universal unique identifier for a guest virtual machine in the VMware NSX environment.
   */
  @Nullable
  @Index(55)
  String destinationVmUuid();

  /**
   * ID of the tunnel being inspected or the International Mobile Subscriber Identity (IMSI) ID of the mobile user.
   *
   * @return
   *     ID of the tunnel being inspected or the International Mobile Subscriber Identity (IMSI) ID of the mobile user.
   */
  @Nullable
  @Index(56)
  String tunnelIdImsi();

  /**
   * Monitor name you configured for the Tunnel Inspection policy rule or the International Mobile Equipment Identity (IMEI) ID of the mobile device.
   *
   * @return
   *     Monitor name you configured for the Tunnel Inspection policy rule or the International Mobile Equipment Identity (IMEI) ID of the mobile device.
   */
  @Nullable
  @Index(57)
  String monitorTagImei();

  /**
   * ID of the session in which this session is tunneled. Applies to inner tunnel (if two levels of tunneling) or inside content (if one level of tunneling) only.
   *
   * @return
   *     ID of the session in which this session is tunneled. Applies to inner tunnel (if two levels of tunneling) or inside content (if one level of tunneling) only.
   */
  @Nullable
  @Index(58)
  String parentSessionId();

  /**
   * Year/month/day hours:minutes:seconds that the parent tunnel session began.
   *
   * @return
   *     Year/month/day hours:minutes:seconds that the parent tunnel session began.
   */
  @Nullable
  @Index(59)
  OffsetDateTime parentStartTime();

  /**
   * Type of tunnel, such as GRE or IPSec.
   *
   * @return
   *     Type of tunnel, such as GRE or IPSec.
   */
  @Nullable
  @Index(60)
  String tunnelType();

}
