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

