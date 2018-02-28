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
|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Threat/Content Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Source IP|[java.net.InetAddress](https:/docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)|
|Destination IP|[java.net.InetAddress](https:/docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)|
|NAT Source IP|[java.net.InetAddress](https:/docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)|
|NAT Destination IP|[java.net.InetAddress](https:/docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)|
|Rule Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Source User|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Destination User|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Application|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Source Zone|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Destination Zone|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Inbound Interface|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Outbound Interface|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Log Action|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Session ID|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Repeat Count|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Source Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|Destination Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|NAT Source Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|NAT Destination Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|Flags|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Protocol|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Action|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Bytes|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Bytes Sent|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Bytes Received|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Packets|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Start Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Elapsed Time|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Category|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Source Location|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Destination Location|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Packets Sent|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Packets Received|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Session End Reason|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Action Source|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Source VM UUID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Destination VM UUID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Tunnel ID/IMSI|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Monitor Tag/IMEI|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Parent Session ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Parent Start Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Tunnel Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|

# ConfigLogMessage

|Name|Type|
|----|----|
|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Subtype|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Host|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Command|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Admin|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Client|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Result|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Configuration Path|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Before Change Detail|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|After Change Detail|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|

# AuthenticationLogMessage

|Name|Type|
|----|----|
|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Subtype|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Host|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Command|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Admin|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Client|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Result|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Configuration Path|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Before Change Detail|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|After Change Detail|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|

# ThreatLogMessage

|Name|Type|
|----|----|
|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Threat/Content Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Source IP|[java.net.InetAddress](https:/docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)|
|Destination IP|[java.net.InetAddress](https:/docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)|
|NAT Source IP|[java.net.InetAddress](https:/docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)|
|NAT Destination IP|[java.net.InetAddress](https:/docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)|
|Rule Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Source User|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Destination User|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Application|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Source Zone|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Destination Zone|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Inbound Interface|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Outbound Interface|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Log Action|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Session ID|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Repeat Count|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Source Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|Destination Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|NAT Source Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|NAT Destination Port|[java.lang.Integer](https:/docs.oracle.com/javase/8/docs/api/java/lang/Integer.html)|
|Flags|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Protocol|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Action|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|URL/Filename|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Threat ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Category|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Severity|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Direction|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Source Location|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Destination Location|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Content Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|PCAP ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|File Digest|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Cloud|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|URL Index|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|User Agent|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|File Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|X-Forwarded-For|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Referer|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Sender|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Subject|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Recipient|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Report ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Source VM UUID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Destination VM UUID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|HTTP Method|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Tunnel ID/IMSI|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Monitor Tag/IMEI|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Parent Session ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Parent Start Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Tunnel Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Threat Category|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Content Version|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|

# UserIdLogMessage

|Name|Type|
|----|----|
|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Threat/Content Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|

# HipMatchLogMessage

|Name|Type|
|----|----|
|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Source User|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Machine Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|OS|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Source IP|[java.net.InetAddress](https:/docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)|
|HIP|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Repeat Count|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|HIP Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|IPv6 Source Ip|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|

# SystemLogMessage

|Name|Type|
|----|----|
|Receive Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Serial Number|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Content/Threat Type|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Generated Time|[java.util.Date](https:/docs.oracle.com/javase/8/docs/api/java/util/Date.html)|
|Virtual System|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Event ID|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Object|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Module|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Severity|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Description|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Sequence Number|[java.lang.Long](https:/docs.oracle.com/javase/8/docs/api/java/lang/Long.html)|
|Action Flags|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 1|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 2|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 3|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Group Hierarchy Level 4|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Virtual System Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|
|Device Name|[java.lang.String](https:/docs.oracle.com/javase/8/docs/api/java/lang/String.html)|