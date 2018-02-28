package com.github.jcustenborder.netty.paloalto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jcustenborder.netty.syslog.BSDSyslogMessage;

public class BaseTestCase<M extends PaloAltoMessage> {
  @JsonProperty
  public BSDSyslogMessage input;
  @JsonProperty
  public M expected;
}
