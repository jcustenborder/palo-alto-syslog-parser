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

|Name|Type|
|----|----|
|Receive Time|java.util.Date|
|Serial Number|java.lang.String|
|Type|java.lang.String|
|Threat/Content Type|java.lang.String|
|Generated Time|java.util.Date|
|Source IP|java.net.InetAddress|
|Destination IP|java.net.InetAddress|
|NAT Source IP|java.net.InetAddress|
|NAT Destination IP|java.net.InetAddress|
|Rule Name|java.lang.String|
|Source User|java.lang.String|
|Destination User|java.lang.String|
|Application|java.lang.String|
|Virtual System|java.lang.String|
|Source Zone|java.lang.String|
|Destination Zone|java.lang.String|
|Inbound Interface|java.lang.String|
|Outbound Interface|java.lang.String|
|Log Action|java.lang.String|
|Session ID|java.lang.Long|
|Repeat Count|java.lang.Long|
|Source Port|java.lang.Integer|
|Destination Port|java.lang.Integer|
|NAT Source Port|java.lang.Integer|
|NAT Destination Port|java.lang.Integer|
|Flags|java.lang.Long|
|Protocol|java.lang.String|
|Action|java.lang.String|
|Bytes|java.lang.Long|
|Bytes Sent|java.lang.Long|
|Bytes Received|java.lang.Long|
|Packets|java.lang.Long|
|Start Time|java.util.Date|
|Elapsed Time|java.lang.Long|
|Category|java.lang.String|
|Sequence Number|java.lang.Long|
|Action Flags|java.lang.String|
|Source Location|java.lang.String|
|Destination Location|java.lang.String|
|Packets Sent|java.lang.Long|
|Packets Received|java.lang.Long|
|Session End Reason|java.lang.String|
|Device Group Hierarchy Level 1|java.lang.String|
|Device Group Hierarchy Level 2|java.lang.String|
|Device Group Hierarchy Level 3|java.lang.String|
|Device Group Hierarchy Level 4|java.lang.String|
|Virtual System Name|java.lang.String|
|Device Name|java.lang.String|
|Action Source|java.lang.String|
|Source VM UUID|java.lang.String|
|Destination VM UUID|java.lang.String|
|Tunnel ID/IMSI|java.lang.String|
|Monitor Tag/IMEI|java.lang.String|
|Parent Session ID|java.lang.String|
|Parent Start Time|java.util.Date|
|Tunnel Type|java.lang.String|

# ConfigLogMessage

|Name|Type|
|----|----|
|Receive Time|java.util.Date|
|Serial Number|java.lang.String|
|Type|java.lang.String|
|Subtype|java.lang.String|
|Generated Time|java.util.Date|
|Host|java.lang.String|
|Virtual System|java.lang.String|
|Command|java.lang.String|
|Admin|java.lang.String|
|Client|java.lang.String|
|Result|java.lang.String|
|Configuration Path|java.lang.String|
|Before Change Detail|java.lang.String|
|After Change Detail|java.lang.String|
|Sequence Number|java.lang.Long|
|Action Flags|java.lang.String|
|Device Group Hierarchy Level 1|java.lang.String|
|Device Group Hierarchy Level 2|java.lang.String|
|Device Group Hierarchy Level 3|java.lang.String|
|Device Group Hierarchy Level 4|java.lang.String|
|Virtual System Name|java.lang.String|
|Device Name|java.lang.String|

# AuthenticationLogMessage

|Name|Type|
|----|----|
|Receive Time|java.util.Date|
|Serial Number|java.lang.String|
|Type|java.lang.String|
|Subtype|java.lang.String|
|Generated Time|java.util.Date|
|Host|java.lang.String|
|Virtual System|java.lang.String|
|Command|java.lang.String|
|Admin|java.lang.String|
|Client|java.lang.String|
|Result|java.lang.String|
|Configuration Path|java.lang.String|
|Before Change Detail|java.lang.String|
|After Change Detail|java.lang.String|
|Sequence Number|java.lang.Long|
|Action Flags|java.lang.String|
|Device Group Hierarchy Level 1|java.lang.String|
|Device Group Hierarchy Level 2|java.lang.String|
|Device Group Hierarchy Level 3|java.lang.String|
|Device Group Hierarchy Level 4|java.lang.String|
|Virtual System Name|java.lang.String|
|Device Name|java.lang.String|

# ThreatLogMessage

|Name|Type|
|----|----|
|Receive Time|java.util.Date|
|Serial Number|java.lang.String|
|Type|java.lang.String|
|Threat/Content Type|java.lang.String|
|Generated Time|java.util.Date|
|Source IP|java.net.InetAddress|
|Destination IP|java.net.InetAddress|
|NAT Source IP|java.net.InetAddress|
|NAT Destination IP|java.net.InetAddress|
|Rule Name|java.lang.String|
|Source User|java.lang.String|
|Destination User|java.lang.String|
|Application|java.lang.String|
|Virtual System|java.lang.String|
|Source Zone|java.lang.String|
|Destination Zone|java.lang.String|
|Inbound Interface|java.lang.String|
|Outbound Interface|java.lang.String|
|Log Action|java.lang.String|
|Session ID|java.lang.Long|
|Repeat Count|java.lang.Long|
|Source Port|java.lang.Integer|
|Destination Port|java.lang.Integer|
|NAT Source Port|java.lang.Integer|
|NAT Destination Port|java.lang.Integer|
|Flags|java.lang.Long|
|Protocol|java.lang.String|
|Action|java.lang.String|
|URL/Filename|java.lang.String|
|Threat ID|java.lang.String|
|Category|java.lang.String|
|Severity|java.lang.String|
|Direction|java.lang.String|
|Sequence Number|java.lang.Long|
|Action Flags|java.lang.String|
|Source Location|java.lang.String|
|Destination Location|java.lang.String|
|Content Type|java.lang.String|
|PCAP ID|java.lang.String|
|File Digest|java.lang.String|
|Cloud|java.lang.String|
|URL Index|java.lang.String|
|User Agent|java.lang.String|
|File Type|java.lang.String|
|X-Forwarded-For|java.lang.String|
|Referer|java.lang.String|
|Sender|java.lang.String|
|Subject|java.lang.String|
|Recipient|java.lang.String|
|Report ID|java.lang.String|
|Device Group Hierarchy Level 1|java.lang.String|
|Device Group Hierarchy Level 2|java.lang.String|
|Device Group Hierarchy Level 3|java.lang.String|
|Device Group Hierarchy Level 4|java.lang.String|
|Virtual System Name|java.lang.String|
|Device Name|java.lang.String|
|Source VM UUID|java.lang.String|
|Destination VM UUID|java.lang.String|
|HTTP Method|java.lang.String|
|Tunnel ID/IMSI|java.lang.String|
|Monitor Tag/IMEI|java.lang.String|
|Parent Session ID|java.lang.String|
|Parent Start Time|java.util.Date|
|Tunnel Type|java.lang.String|
|Threat Category|java.lang.String|
|Content Version|java.lang.String|

# UserIdLogMessage

|Name|Type|
|----|----|
|Receive Time|java.util.Date|
|Serial Number|java.lang.String|
|Type|java.lang.String|
|Threat/Content Type|java.lang.String|
|Generated Time|java.util.Date|

# HipMatchLogMessage

|Name|Type|
|----|----|
|Receive Time|java.util.Date|
|Serial Number|java.lang.String|
|Type|java.lang.String|
|Generated Time|java.util.Date|
|Source User|java.lang.String|
|Virtual System|java.lang.String|
|Machine Name|java.lang.String|
|OS|java.lang.String|
|Source IP|java.net.InetAddress|
|HIP|java.lang.String|
|Repeat Count|java.lang.Long|
|HIP Type|java.lang.String|
|Sequence Number|java.lang.Long|
|Action Flags|java.lang.String|
|Device Group Hierarchy Level 1|java.lang.String|
|Device Group Hierarchy Level 2|java.lang.String|
|Device Group Hierarchy Level 3|java.lang.String|
|Device Group Hierarchy Level 4|java.lang.String|
|Virtual System Name|java.lang.String|
|Device Name|java.lang.String|
|Virtual System ID|java.lang.String|
|IPv6 Source Ip|java.lang.String|

# SystemLogMessage

|Name|Type|
|----|----|
|Receive Time|java.util.Date|
|Serial Number|java.lang.String|
|Type|java.lang.String|
|Content/Threat Type|java.lang.String|
|Generated Time|java.util.Date|
|Virtual System|java.lang.String|
|Event ID|java.lang.String|
|Object|java.lang.String|
|Module|java.lang.String|
|Severity|java.lang.String|
|Description|java.lang.String|
|Sequence Number|java.lang.Long|
|Action Flags|java.lang.String|
|Device Group Hierarchy Level 1|java.lang.String|
|Device Group Hierarchy Level 2|java.lang.String|
|Device Group Hierarchy Level 3|java.lang.String|
|Device Group Hierarchy Level 4|java.lang.String|
|Virtual System Name|java.lang.String|
|Device Name|java.lang.String|

