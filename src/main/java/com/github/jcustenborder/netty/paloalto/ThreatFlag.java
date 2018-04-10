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

import java.util.ArrayList;
import java.util.List;

public enum ThreatFlag {
  /**
   * 0x80000000 —session has a packet capture (PCAP)
   */
  PCAP(0x80000000),
  /**
   * 0x02000000 —IPv6 session
   */
  IPV6(0x02000000),
  /**
   * 0x01000000 —SSL session was decrypted (SSL Proxy)
   */
  SSLProxy(0x01000000),
  /**
   * 0x00800000 —session was denied via URL filtering
   */
  URLFilter(0x00800000),
  /**
   * 0x00400000 —session has a NAT translation performed (NAT)
   */
  NATTranslation(0x00400000),
  /**
   * 0x00200000 —user information for the session was captured through Captive Portal
   */
  CaptivePortal(0x00200000),
  /**
   * 0x00080000 —X-Forwarded-For value from a proxy is in the source user field
   */
  XForwardedFor(0x00080000),
  /**
   * 0x00040000 —log corresponds to a transaction within a http proxy session (Proxy Transaction)
   */
  ProxyTransaction(0x00040000),
  /**
   * 0x00008000 —session is a container page access (Container Page)
   */
  ContainerPage(0x00008000),
  /**
   * 0x00002000 —session has a temporary match on a rule for implicit application dependency handling. Available in PAN-OS 5.0.0 and above.
   */
  ImplicitApplicationDependencyHandling(0x00002000),
  /**
   * 0x00000800 —symmetric return was used to forward traffic for this session
   */
  SymmetricReturn(0x00000800);


  private final int value;

  ThreatFlag(int v) {
    this.value = v;
  }


  public static List<ThreatFlag> parse(int input) {
    List<ThreatFlag> result = new ArrayList<>();

    for (ThreatFlag f : ThreatFlag.values()) {
      if ((input & f.value) == 1) {
        result.add(f);
      }
    }

    return result;
  }
}
