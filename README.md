[![Maven Central](https://img.shields.io/maven-central/v/com.github.jcustenborder.netty/palo-alto-syslog-parser.svg)]()

# Introduction

This project provides an extended MessageToMessageDecoder to process syslog messages received by 
[netty-codec-syslog](https://github.com/jcustenborder/netty-codec-syslog). This works by receiving
[RFC3164Message](https://github.com/jcustenborder/netty-codec-syslog/blob/master/src/main/java/com/github/jcustenborder/netty/syslog/RFC3164Message.java)s
and parsing the message portion of the RFC3164Message into the proper PaloAltoMessage. To use this
library you will need to have an understanding of [Netty](http://netty.io).

## Usage 

Add the message encoder to the existing pipeline.

```java
    ServerBootstrap b = new ServerBootstrap(); // (2)
    b.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class) // (3)
        .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
          @Override
          public void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(
                new LoggingHandler("Syslog", LogLevel.INFO),
                new DelimiterBasedFrameDecoder(2000, true, Delimiters.lineDelimiter()),
                new TCPSyslogMessageDecoder(),
                new SyslogMessageDecoder(),
                new PaloAltoMessageDecoder(),
                new MyPaloAltoMessageHandler()
            );
          }
        })
        .option(ChannelOption.SO_BACKLOG, 128)          // (5)
        .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
```

Subscribe to receive the messages you are interested in.

```java
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

class MyPaloAltoMessageHandler extends SimpleChannelInboundHandler<TrafficLogMessage> {
  

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, TrafficLogMessage message) throws Exception {
  
  }
} 
```

# TrafficLogMessage

|Name|Description|Type|
|----|-----------|----|
|receiveTime|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|serialNumber|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|type|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|threatContentType|Threat/Content Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|generatedTime|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|sourceIp|Source IP|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|destinationIp|Destination IP|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|natSourceIp|NAT Source IP|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|natDestinationIp|NAT Destination IP|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|ruleName|Rule Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sourceUser|Source User|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|destinationUser|Destination User|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|application|Application|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystem|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sourceZone|Source Zone|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|destinationZone|Destination Zone|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|inboundInterface|Inbound Interface|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|outboundInterface|Outbound Interface|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|logAction|Log Action|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sessionId|Session ID|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|repeatCount|Repeat Count|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|sourcePort|Source Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|destinationPort|Destination Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|natSourcePort|NAT Source Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|natDestinationPort|NAT Destination Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|flags|Flags|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|protocol|Protocol|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|action|Action|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|bytes|Bytes|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|bytesSent|Bytes Sent|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|bytesReceived|Bytes Received|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|packets|Packets|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|startTime|Start Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|elapsedTime|Elapsed Time|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|category|Category|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sequenceNumber|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|actionFlags|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sourceLocation|Source Location|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|destinationLocation|Destination Location|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|packetsSent|Packets Sent|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|packetsReceived|Packets Received|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|sessionEndReason|Session End Reason|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel1|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel2|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel3|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel4|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystemName|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceName|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|actionSource|Action Source|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sourceVmUuid|Source VM UUID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|destinationVmUuid|Destination VM UUID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|tunnelIdImsi|Tunnel ID/IMSI|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|monitorTagImei|Monitor Tag/IMEI|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|parentSessionId|Parent Session ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|parentStartTime|Parent Start Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|tunnelType|Tunnel Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|

# ConfigLogMessage

|Name|Description|Type|
|----|-----------|----|
|receiveTime|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|serialNumber|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|type|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|subtype|Subtype|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|generatedTime|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|host|Host|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystem|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|command|Command|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|admin|Admin|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|client|Client|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|result|Result|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|configurationPath|Configuration Path|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|beforeChangeDetail|Before Change Detail|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|afterChangeDetail|After Change Detail|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sequenceNumber|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|actionFlags|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel1|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel2|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel3|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel4|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystemName|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceName|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|

# AuthenticationLogMessage

|Name|Description|Type|
|----|-----------|----|
|receiveTime|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|serialNumber|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|type|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|subtype|Subtype|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|generatedTime|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|host|Host|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystem|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|command|Command|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|admin|Admin|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|client|Client|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|result|Result|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|configurationPath|Configuration Path|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|beforeChangeDetail|Before Change Detail|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|afterChangeDetail|After Change Detail|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sequenceNumber|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|actionFlags|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel1|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel2|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel3|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel4|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystemName|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceName|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|

# ThreatLogMessage

|Name|Description|Type|
|----|-----------|----|
|receiveTime|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|serialNumber|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|type|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|threatContentType|Threat/Content Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|generatedTime|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|sourceIp|Source IP|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|destinationIp|Destination IP|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|natSourceIp|NAT Source IP|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|natDestinationIp|NAT Destination IP|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|ruleName|Rule Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sourceUser|Source User|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|destinationUser|Destination User|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|application|Application|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystem|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sourceZone|Source Zone|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|destinationZone|Destination Zone|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|inboundInterface|Inbound Interface|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|outboundInterface|Outbound Interface|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|logAction|Log Action|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sessionId|Session ID|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|repeatCount|Repeat Count|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|sourcePort|Source Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|destinationPort|Destination Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|natSourcePort|NAT Source Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|natDestinationPort|NAT Destination Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|flags|Flags|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|protocol|Protocol|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|action|Action|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|urlFilename|URL/Filename|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|threatId|Threat ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|category|Category|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|severity|Severity|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|direction|Direction|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sequenceNumber|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|actionFlags|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sourceLocation|Source Location|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|destinationLocation|Destination Location|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|contentType|Content Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|pcapId|PCAP ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|fileDigest|File Digest|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|cloud|Cloud|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|urlIndex|URL Index|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|userAgent|User Agent|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|fileType|File Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|xForwardedFor|X-Forwarded-For|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|referer|Referer|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sender|Sender|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|subject|Subject|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|recipient|Recipient|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|reportId|Report ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel1|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel2|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel3|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel4|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystemName|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceName|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sourceVmUuid|Source VM UUID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|destinationVmUuid|Destination VM UUID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|httpMethod|HTTP Method|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|tunnelIdImsi|Tunnel ID/IMSI|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|monitorTagImei|Monitor Tag/IMEI|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|parentSessionId|Parent Session ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|parentStartTime|Parent Start Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|tunnelType|Tunnel Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|threatCategory|Threat Category|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|contentVersion|Content Version|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|

# UserIdLogMessage

|Name|Description|Type|
|----|-----------|----|
|receiveTime|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|serialNumber|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|type|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|threatContentType|Threat/Content Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|generatedTime|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|

# HipMatchLogMessage

|Name|Description|Type|
|----|-----------|----|
|receiveTime|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|serialNumber|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|type|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|generatedTime|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|sourceUser|Source User|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystem|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|machineName|Machine Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|os|OS|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sourceIp|Source IP|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|hip|HIP|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|repeatCount|Repeat Count|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|hipType|HIP Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sequenceNumber|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|actionFlags|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel1|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel2|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel3|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel4|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystemName|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceName|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystemId|Virtual System ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|ipv6SourceIp|IPv6 Source Ip|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|

# SystemLogMessage

|Name|Description|Type|
|----|-----------|----|
|receiveTime|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|serialNumber|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|type|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|subType|Content/Threat Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|generatedTime|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|virtualSystem|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|eventId|Event ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|object|Object|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|module|Module|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|severity|Severity|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|description|Description|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|sequenceNumber|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|actionFlags|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel1|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel2|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel3|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceGroupHierarchyLevel4|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|virtualSystemName|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|deviceName|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|

