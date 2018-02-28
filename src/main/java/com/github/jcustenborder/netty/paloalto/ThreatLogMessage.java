
package com.github.jcustenborder.netty.paloalto;

import java.net.InetAddress;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.jcustenborder.netty.syslog.Nullable;

@JsonDeserialize(as = ImmutableThreatLogMessage.class)
@JsonSerialize(as = ImmutableThreatLogMessage.class)
@org.immutables.value.Value.Immutable
public interface ThreatLogMessage
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
     * Time the log was generated on the dataplane.
     * 
     * @return
     *     Time the log was generated on the dataplane.
     */
    @Nullable
    @Index(6)
    Date generatedTime();

    /**
     * Original session source IP address.
     * 
     * @return
     *     Original session source IP address.
     */
    @Nullable
    @Index(7)
    InetAddress sourceIp();

    /**
     * Original session destination IP address.
     * 
     * @return
     *     Original session destination IP address.
     */
    @Nullable
    @Index(8)
    InetAddress destinationIp();

    /**
     * If source NAT performed, the post-NAT source IP address.
     * 
     * @return
     *     If source NAT performed, the post-NAT source IP address.
     */
    @Nullable
    @Index(9)
    InetAddress natSourceIp();

    /**
     * If destination NAT performed, the post-NAT destination IP address.
     * 
     * @return
     *     If destination NAT performed, the post-NAT destination IP address.
     */
    @Nullable
    @Index(10)
    InetAddress natDestinationIp();

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
     * Number of sessions with same Source IP, Destination IP, Application, and Content/Threat Type seen within 5 seconds; used for ICMP only.
     * 
     * @return
     *     Number of sessions with same Source IP, Destination IP, Application, and Content/Threat Type seen within 5 seconds; used for ICMP only.
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
     *  0x80000000 - session has a packet capture (PCAP)
     *  0x02000000 - IPv6 session
     *  0x01000000 - SSL session was decrypted (SSL Proxy)
     *  0x00800000 - session was denied via URL filtering
     *  0x00400000 - session has a NAT translation performed (NAT)
     *  0x00200000 - user information for the session was captured through Captive Portal
     *  0x00080000 - X-Forwarded-For value from a proxy is in the source user field
     *  0x00040000 - log corresponds to a transaction within a http proxy session (Proxy Transaction)
     *  0x00008000 - session is a container page access (Container Page)
     *  0x00002000 - session has a temporary match on a rule for implicit application dependency handling. Available in PAN-OS 5.0.0 and above
     *  0x00000800 - symmetric return was used to forward traffic for this session
     * 
     * @return
     *      32-bit field that provides details on session; this field can be decoded by AND-ing the values with the logged value:
     *      0x80000000 - session has a packet capture (PCAP)
     *      0x02000000 - IPv6 session
     *      0x01000000 - SSL session was decrypted (SSL Proxy)
     *      0x00800000 - session was denied via URL filtering
     *      0x00400000 - session has a NAT translation performed (NAT)
     *      0x00200000 - user information for the session was captured through Captive Portal
     *      0x00080000 - X-Forwarded-For value from a proxy is in the source user field
     *      0x00040000 - log corresponds to a transaction within a http proxy session (Proxy Transaction)
     *      0x00008000 - session is a container page access (Container Page)
     *      0x00002000 - session has a temporary match on a rule for implicit application dependency handling. Available in PAN-OS 5.0.0 and above
     *      0x00000800 - symmetric return was used to forward traffic for this session
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
     * Action taken for the session; values are alert, allow, deny, drop, drop-all-packets, reset-client, reset-server, reset-both, block-url.
     * alert - threat or URL detected but not blocked
     * allow - flood detection alert
     * deny - flood detection mechanism activated and deny traffic based on configuration
     * drop - threat detected and associated session was dropped
     * reset-client - threat detected and a TCP RST is sent to the client
     * reset-server - threat detected and a TCP RST is sent to the server
     * reset-both - threat detected and a TCP RST is sent to both the client and the server
     * block-url - URL request was blocked because it matched a URL category that was set to be blocked
     * block-ip - threat detected and client IP is blocked
     * random-drop - flood detected and packet was randomly dropped
     * sinkhole - DNS sinkhole activated
     * syncookie-sent - syncookie alert
     * block-continue (URL subtype only) - a HTTP request is blocked and redirected to a Continue page with a button for confirmation to proceed
     * continue (URL subtype only) - response to a block-continue URL continue page indicating a block-continue request was allowed to proceed
     * block-override (URL subtype only) - a HTTP request is blocked and redirected to an Admin override page that requires a pass code from the firewall administrator to continue
     * override-lockout (URL subtype only) - too many failed admin override pass code attempts from the source IP. IP is now blocked from the block-override redirect page
     * override (URL subtype only) - response to a block-override page where a correct pass code is provided and the request is allowed
     * block (Wildfire only) - file was blocked by the firewall and uploaded to Wildfire
     * 
     * @return
     *     Action taken for the session; values are alert, allow, deny, drop, drop-all-packets, reset-client, reset-server, reset-both, block-url.
     *     alert - threat or URL detected but not blocked
     *     allow - flood detection alert
     *     deny - flood detection mechanism activated and deny traffic based on configuration
     *     drop - threat detected and associated session was dropped
     *     reset-client - threat detected and a TCP RST is sent to the client
     *     reset-server - threat detected and a TCP RST is sent to the server
     *     reset-both - threat detected and a TCP RST is sent to both the client and the server
     *     block-url - URL request was blocked because it matched a URL category that was set to be blocked
     *     block-ip - threat detected and client IP is blocked
     *     random-drop - flood detected and packet was randomly dropped
     *     sinkhole - DNS sinkhole activated
     *     syncookie-sent - syncookie alert
     *     block-continue (URL subtype only) - a HTTP request is blocked and redirected to a Continue page with a button for confirmation to proceed
     *     continue (URL subtype only) - response to a block-continue URL continue page indicating a block-continue request was allowed to proceed
     *     block-override (URL subtype only) - a HTTP request is blocked and redirected to an Admin override page that requires a pass code from the firewall administrator to continue
     *     override-lockout (URL subtype only) - too many failed admin override pass code attempts from the source IP. IP is now blocked from the block-override redirect page
     *     override (URL subtype only) - response to a block-override page where a correct pass code is provided and the request is allowed
     *     block (Wildfire only) - file was blocked by the firewall and uploaded to Wildfire
     */
    @Nullable
    @Index(30)
    String action();

    /**
     * Field with variable length with a maximum of 1023 characters
     * The actual URI when the subtype is URL
     * File name or file type when the subtype is file
     * File name when the subtype is virus
     * File name when the subtype is WildFire
     * 
     * @return
     *     Field with variable length with a maximum of 1023 characters
     *     The actual URI when the subtype is URL
     *     File name or file type when the subtype is file
     *     File name when the subtype is virus
     *     File name when the subtype is WildFire
     */
    @Nullable
    @Index(31)
    String urlFilename();

    /**
     * Threat ID ranges for virus detection, WildFire signature feed, and DNS C2 signatures used in previous releases have been replaced with permanent, globally unique IDs . Refer to the Threat/Content Type and Threat Category (thr_category) field names to create updated reports, filter threat logs, and ACC activity.
     * 
     * @return
     *     Threat ID ranges for virus detection, WildFire signature feed, and DNS C2 signatures used in previous releases have been replaced with permanent, globally unique IDs . Refer to the Threat/Content Type and Threat Category (thr_category) field names to create updated reports, filter threat logs, and ACC activity.
     */
    @Nullable
    @Index(32)
    String threatId();

    /**
     * For URL Subtype, it is the URL Category; For WildFire subtype, it is the verdict on the file and is either ‘malicious’, ‘phishing’, ‘grayware’, or ‘benign’; For other subtypes, the value is ‘any’.
     * 
     * @return
     *     For URL Subtype, it is the URL Category; For WildFire subtype, it is the verdict on the file and is either ‘malicious’, ‘phishing’, ‘grayware’, or ‘benign’; For other subtypes, the value is ‘any’.
     */
    @Nullable
    @Index(33)
    String category();

    /**
     * Severity associated with the threat; values are informational, low, medium, high, critical.
     * 
     * @return
     *     Severity associated with the threat; values are informational, low, medium, high, critical.
     */
    @Nullable
    @Index(34)
    String severity();

    /**
     * Indicates the direction of the attack, client-to-server or server-to-client:
     *  0 - direction of the threat is client to server
     *  1 - direction of the threat is server to client
     * 
     * @return
     *     Indicates the direction of the attack, client-to-server or server-to-client:
     *      0 - direction of the threat is client to server
     *      1 - direction of the threat is server to client
     */
    @Nullable
    @Index(35)
    String direction();

    /**
     * A 64-bit log entry identifier incremented sequentially. Each log type has a unique number space.
     * 
     * @return
     *     A 64-bit log entry identifier incremented sequentially. Each log type has a unique number space.
     */
    @Nullable
    @Index(36)
    Long sequenceNumber();

    /**
     * A bit field indicating if the log was forwarded to Panorama.
     * 
     * @return
     *     A bit field indicating if the log was forwarded to Panorama.
     */
    @Nullable
    @Index(37)
    String actionFlags();

    /**
     * Source country or Internal region for private addresses. Maximum length is 32 bytes.
     * 
     * @return
     *     Source country or Internal region for private addresses. Maximum length is 32 bytes.
     */
    @Nullable
    @Index(38)
    String sourceLocation();

    /**
     * Destination country or Internal region for private addresses. Maximum length is 32 bytes.
     * 
     * @return
     *     Destination country or Internal region for private addresses. Maximum length is 32 bytes.
     */
    @Nullable
    @Index(39)
    String destinationLocation();

    /**
     * Applicable only when Subtype is URL.
     * Content type of the HTTP response data. Maximum length 32 bytes.
     * 
     * @return
     *     Applicable only when Subtype is URL.
     *     Content type of the HTTP response data. Maximum length 32 bytes.
     */
    @Nullable
    @Index(41)
    String contentType();

    /**
     * The packet capture (pcap) ID is a 64 bit unsigned integral denoting an ID to correlate threat pcap files with extended pcaps taken as a part of that flow. All threat logs will contain either a pcap_id of 0 (no associated pcap), or an ID referencing the extended pcap file.
     * 
     * @return
     *     The packet capture (pcap) ID is a 64 bit unsigned integral denoting an ID to correlate threat pcap files with extended pcaps taken as a part of that flow. All threat logs will contain either a pcap_id of 0 (no associated pcap), or an ID referencing the extended pcap file.
     */
    @Nullable
    @Index(42)
    String pcapId();

    /**
     * Only for WildFire subtype; all other types do not use this field
     * The filedigest string shows the binary hash of the file sent to be analyzed by the WildFire service.
     * 
     * @return
     *     Only for WildFire subtype; all other types do not use this field
     *     The filedigest string shows the binary hash of the file sent to be analyzed by the WildFire service.
     */
    @Nullable
    @Index(43)
    String fileDigest();

    /**
     * Only for WildFire subtype; all other types do not use this field.
     * The cloud string displays the FQDN of either the WildFire appliance (private) or the WildFire cloud (public) from where the file was uploaded for analysis.
     * 
     * @return
     *     Only for WildFire subtype; all other types do not use this field.
     *     The cloud string displays the FQDN of either the WildFire appliance (private) or the WildFire cloud (public) from where the file was uploaded for analysis.
     */
    @Nullable
    @Index(44)
    String cloud();

    /**
     * Used in URL Filtering and WildFire subtypes.
     * When an application uses TCP keepalives to keep a connection open for a length of time, all the log entries for that session have a single session ID. In such cases, when you have a single threat log (and session ID) that includes multiple URL entries, the url_idx is a counter that allows you to correlate the order of each log entry within the single session.
     * For example, to learn the URL of a file that the firewall forwarded to WildFire for analysis, locate the session ID and the url_idx from the WildFire Submissions log and search for the same session ID and url_idx in your URL filtering logs. The log entry that matches the session ID and url_idx will contain the URL of the file that was forwarded to WildFire.
     * 
     * @return
     *     Used in URL Filtering and WildFire subtypes.
     *     When an application uses TCP keepalives to keep a connection open for a length of time, all the log entries for that session have a single session ID. In such cases, when you have a single threat log (and session ID) that includes multiple URL entries, the url_idx is a counter that allows you to correlate the order of each log entry within the single session.
     *     For example, to learn the URL of a file that the firewall forwarded to WildFire for analysis, locate the session ID and the url_idx from the WildFire Submissions log and search for the same session ID and url_idx in your URL filtering logs. The log entry that matches the session ID and url_idx will contain the URL of the file that was forwarded to WildFire.
     */
    @Nullable
    @Index(45)
    String urlIndex();

    /**
     * Only for the URL Filtering subtype; all other types do not use this field.
     * The User Agent field specifies the web browser that the user used to access the URL, for example Internet Explorer. This information is sent in the HTTP request to the server.
     * 
     * @return
     *     Only for the URL Filtering subtype; all other types do not use this field.
     *     The User Agent field specifies the web browser that the user used to access the URL, for example Internet Explorer. This information is sent in the HTTP request to the server.
     */
    @Nullable
    @Index(46)
    String userAgent();

    /**
     * Only for WildFire subtype; all other types do not use this field.
     * Specifies the type of file that the firewall forwarded for WildFire analysis.
     * 
     * @return
     *     Only for WildFire subtype; all other types do not use this field.
     *     Specifies the type of file that the firewall forwarded for WildFire analysis.
     */
    @Nullable
    @Index(47)
    String fileType();

    /**
     * Only for the URL Filtering subtype; all other types do not use this field.
     * The X-Forwarded-For field in the HTTP header contains the IP address of the user who requested the web page. It allows you to identify the IP address of the user, which is useful particularly if you have a proxy server on your network that replaces the user IP address with its own address in the source IP address field of the packet header.
     * 
     * @return
     *     Only for the URL Filtering subtype; all other types do not use this field.
     *     The X-Forwarded-For field in the HTTP header contains the IP address of the user who requested the web page. It allows you to identify the IP address of the user, which is useful particularly if you have a proxy server on your network that replaces the user IP address with its own address in the source IP address field of the packet header.
     */
    @Nullable
    @Index(48)
    String xForwardedFor();

    /**
     * Only for the URL Filtering subtype; all other types do not use this field.
     * The Referer field in the HTTP header contains the URL of the web page that linked the user to another web page; it is the source that redirected (referred) the user to the web page that is being requested.
     * 
     * @return
     *     Only for the URL Filtering subtype; all other types do not use this field.
     *     The Referer field in the HTTP header contains the URL of the web page that linked the user to another web page; it is the source that redirected (referred) the user to the web page that is being requested.
     */
    @Nullable
    @Index(49)
    String referer();

    /**
     * Only for WildFire subtype; all other types do not use this field.
     * Specifies the name of the sender of an email that WildFire determined to be malicious when analyzing an email link forwarded by the firewall.
     * 
     * @return
     *     Only for WildFire subtype; all other types do not use this field.
     *     Specifies the name of the sender of an email that WildFire determined to be malicious when analyzing an email link forwarded by the firewall.
     */
    @Nullable
    @Index(50)
    String sender();

    /**
     * Only for WildFire subtype; all other types do not use this field.
     * Specifies the subject of an email that WildFire determined to be malicious when analyzing an email link forwarded by the firewall.
     * 
     * @return
     *     Only for WildFire subtype; all other types do not use this field.
     *     Specifies the subject of an email that WildFire determined to be malicious when analyzing an email link forwarded by the firewall.
     */
    @Nullable
    @Index(51)
    String subject();

    /**
     * Only for WildFire subtype; all other types do not use this field.
     * Specifies the name of the receiver of an email that WildFire determined to be malicious when analyzing an email link forwarded by the firewall.
     * 
     * @return
     *     Only for WildFire subtype; all other types do not use this field.
     *     Specifies the name of the receiver of an email that WildFire determined to be malicious when analyzing an email link forwarded by the firewall.
     */
    @Nullable
    @Index(52)
    String recipient();

    /**
     * Only for WildFire subtype; all other types do not use this field.
     * Identifies the analysis request on the WildFire cloud or the WildFire appliance.
     * 
     * @return
     *     Only for WildFire subtype; all other types do not use this field.
     *     Identifies the analysis request on the WildFire cloud or the WildFire appliance.
     */
    @Nullable
    @Index(53)
    String reportId();

    /**
     * A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
     * If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
     * 
     * @return
     *     A sequence of identification numbers that indicate the device group’s location within a device group hierarchy. The firewall (or virtual system) generating the log includes the identification number of each ancestor in its device group hierarchy. The shared device group (level 0) is not included in this structure.
     *     If the log values are 12, 34, 45, 0, it means that the log was generated by a firewall (or virtual system) that belongs to device group 45, and its ancestors are 34, and 12. To view the device group names that correspond to the value 12, 34 or 45, use one of the following methods:
     */
    @Nullable
    @Index(54)
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
    @Index(55)
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
    @Index(56)
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
    @Index(57)
    String deviceGroupHierarchyLevel4();

    /**
     * The name of the virtual system associated with the session; only valid on firewalls enabled for multiple virtual systems.
     * 
     * @return
     *     The name of the virtual system associated with the session; only valid on firewalls enabled for multiple virtual systems.
     */
    @Nullable
    @Index(58)
    String virtualSystemName();

    /**
     * The hostname of the firewall on which the session was logged.
     * 
     * @return
     *     The hostname of the firewall on which the session was logged.
     */
    @Nullable
    @Index(59)
    String deviceName();

    /**
     * Identifies the source universal unique identifier for a guest virtual machine in the VMware NSX environment.
     * 
     * @return
     *     Identifies the source universal unique identifier for a guest virtual machine in the VMware NSX environment.
     */
    @Nullable
    @Index(61)
    String sourceVmUuid();

    /**
     * Identifies the destination universal unique identifier for a guest virtual machine in the VMware NSX environment.
     * 
     * @return
     *     Identifies the destination universal unique identifier for a guest virtual machine in the VMware NSX environment.
     */
    @Nullable
    @Index(62)
    String destinationVmUuid();

    /**
     * Only in URL filtering logs. Describes the HTTP Method used in the web request. Only the following methods are logged: Connect, Delete, Get, Head, Options, Post, Put.
     * 
     * @return
     *     Only in URL filtering logs. Describes the HTTP Method used in the web request. Only the following methods are logged: Connect, Delete, Get, Head, Options, Post, Put.
     */
    @Nullable
    @Index(63)
    String httpMethod();

    /**
     * ID of the tunnel being inspected or the International Mobile Subscriber Identity (IMSI) ID of the mobile user.
     * 
     * @return
     *     ID of the tunnel being inspected or the International Mobile Subscriber Identity (IMSI) ID of the mobile user.
     */
    @Nullable
    @Index(64)
    String tunnelIdImsi();

    /**
     * The user-defined value that groups similar traffic together for logging and reporting. This value is globally defined.
     * 
     * @return
     *     The user-defined value that groups similar traffic together for logging and reporting. This value is globally defined.
     */
    @Nullable
    @Index(65)
    String monitorTagImei();

    /**
     * ID of the session in which this session is tunneled. Applies to inner tunnel (if two levels of tunneling) or inside content (if one level of tunneling) only.
     * 
     * @return
     *     ID of the session in which this session is tunneled. Applies to inner tunnel (if two levels of tunneling) or inside content (if one level of tunneling) only.
     */
    @Nullable
    @Index(66)
    String parentSessionId();

    /**
     * Year/month/day hours:minutes:seconds that the parent tunnel session began.
     * 
     * @return
     *     Year/month/day hours:minutes:seconds that the parent tunnel session began.
     */
    @Nullable
    @Index(67)
    Date parentStartTime();

    /**
     * Type of tunnel, such as GRE or IPSec.
     * 
     * @return
     *     Type of tunnel, such as GRE or IPSec.
     */
    @Nullable
    @Index(68)
    String tunnelType();

    /**
     * Describes threat categories used to classify different types of threat signatures.
     * 
     * @return
     *     Describes threat categories used to classify different types of threat signatures.
     */
    @Nullable
    @Index(69)
    String threatCategory();

    /**
     * Applications and Threats version on your firewall when the log was generated.
     * 
     * @return
     *     Applications and Threats version on your firewall when the log was generated.
     */
    @Nullable
    @Index(70)
    String contentVersion();

}
