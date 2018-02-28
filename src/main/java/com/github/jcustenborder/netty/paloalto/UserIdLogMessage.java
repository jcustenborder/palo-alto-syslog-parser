
package com.github.jcustenborder.netty.paloalto;

import java.net.InetAddress;
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

    @Nullable
    @Index(6)
    Date generatedTime();

    @Nullable
    @Index(7)
    InetAddress sourceIp();

    @Nullable
    @Index(8)
    InetAddress destinationIp();

    @Nullable
    @Index(9)
    InetAddress natSourceIp();

    @Nullable
    @Index(10)
    InetAddress natDestinationIp();

    @Nullable
    @Index(11)
    String ruleName();

    @Nullable
    @Index(12)
    String sourceUser();

    @Nullable
    @Index(13)
    String destinationUser();

    @Nullable
    @Index(14)
    String application();

    @Nullable
    @Index(15)
    String virtualSystem();

    @Nullable
    @Index(16)
    String sourceZone();

    @Nullable
    @Index(17)
    String destinationZone();

    @Nullable
    @Index(18)
    String inboundInterface();

    @Nullable
    @Index(19)
    String outboundInterface();

    @Nullable
    @Index(20)
    String logAction();

    @Nullable
    @Index(22)
    Long sessionId();

    @Nullable
    @Index(23)
    Long repeatCount();

    @Nullable
    @Index(24)
    Integer sourcePort();

    @Nullable
    @Index(25)
    Integer destinationPort();

    @Nullable
    @Index(26)
    Integer natSourcePort();

    @Nullable
    @Index(27)
    Integer natDestinationPort();

    @Nullable
    @Index(28)
    String flags();

    @Nullable
    @Index(29)
    String protocol();

    @Nullable
    @Index(30)
    String action();

    @Nullable
    @Index(31)
    String urlFilename();

    @Nullable
    @Index(32)
    String threatId();

    @Nullable
    @Index(33)
    String category();

    @Nullable
    @Index(34)
    String severity();

    @Nullable
    @Index(35)
    String direction();

    @Nullable
    @Index(36)
    Long sequenceNumber();

    @Nullable
    @Index(37)
    Integer actionFlags();

    @Nullable
    @Index(38)
    String sourceLocation();

    @Nullable
    @Index(39)
    String destinationLocation();

    @Nullable
    @Index(41)
    String contentType();

    @Nullable
    @Index(42)
    String pcapId();

    @Nullable
    @Index(43)
    String fileDigest();

    @Nullable
    @Index(44)
    String cloud();

    @Nullable
    @Index(45)
    String urlIndex();

    @Nullable
    @Index(46)
    String userAgent();

    @Nullable
    @Index(47)
    String fileType();

    @Nullable
    @Index(48)
    String xForwardedFor();

    @Nullable
    @Index(49)
    String referer();

    @Nullable
    @Index(50)
    String sender();

    @Nullable
    @Index(51)
    String subject();

    @Nullable
    @Index(52)
    String recipient();

    @Nullable
    @Index(53)
    String reportId();

    @Nullable
    @Index(54)
    String deviceGroupHierarchyLevel1();

    @Nullable
    @Index(55)
    String deviceGroupHierarchyLevel2();

    @Nullable
    @Index(56)
    String deviceGroupHierarchyLevel3();

    @Nullable
    @Index(57)
    String deviceGroupHierarchyLevel4();

    @Nullable
    @Index(58)
    String virtualSystemName();

    @Nullable
    @Index(59)
    String deviceName();

    @Nullable
    @Index(61)
    String sourceVmUuid();

    @Nullable
    @Index(62)
    String destinationVmUuid();

    @Nullable
    @Index(63)
    String httpMethod();

    @Nullable
    @Index(64)
    String tunnelIdImsi();

    @Nullable
    @Index(65)
    String monitorTagImei();

    @Nullable
    @Index(66)
    String parentSessionId();

    @Nullable
    @Index(67)
    Date parentStartTime();

    @Nullable
    @Index(68)
    String tunnelType();

    @Nullable
    @Index(69)
    String threatCategory();

    @Nullable
    @Index(70)
    String contentVersion();

}
