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

import org.junit.jupiter.api.Assertions;

public class TrafficLogParserTest
    extends PaloAltoParserTest<TrafficLogMessage, TrafficLogParser, TrafficLogParserTestCase>
{


    @Override
    protected TrafficLogParser parser() {
        return new TrafficLogParser();
    }

    @Override
    protected Class<TrafficLogParserTestCase> testCaseClass() {
        return TrafficLogParserTestCase.class;
    }

    @Override
    protected void assertMessage(TrafficLogMessage expected, TrafficLogMessage actual) {
        if (null!= expected) {
            Assertions.assertNotNull(actual, "actual should not be null.");
        } else {
            Assertions.assertNull(actual, "actual should be null.");
        }
        Assertions.assertEquals(expected.threatContentType(), actual.threatContentType(), "threatContentType() should match");
        Assertions.assertEquals(expected.generatedTime(), actual.generatedTime(), "generatedTime() should match");
        Assertions.assertEquals(expected.sourceIp(), actual.sourceIp(), "sourceIp() should match");
        Assertions.assertEquals(expected.destinationIp(), actual.destinationIp(), "destinationIp() should match");
        Assertions.assertEquals(expected.natSourceIp(), actual.natSourceIp(), "natSourceIp() should match");
        Assertions.assertEquals(expected.natDestinationIp(), actual.natDestinationIp(), "natDestinationIp() should match");
        Assertions.assertEquals(expected.ruleName(), actual.ruleName(), "ruleName() should match");
        Assertions.assertEquals(expected.sourceUser(), actual.sourceUser(), "sourceUser() should match");
        Assertions.assertEquals(expected.destinationUser(), actual.destinationUser(), "destinationUser() should match");
        Assertions.assertEquals(expected.application(), actual.application(), "application() should match");
        Assertions.assertEquals(expected.virtualSystem(), actual.virtualSystem(), "virtualSystem() should match");
        Assertions.assertEquals(expected.sourceZone(), actual.sourceZone(), "sourceZone() should match");
        Assertions.assertEquals(expected.destinationZone(), actual.destinationZone(), "destinationZone() should match");
        Assertions.assertEquals(expected.inboundInterface(), actual.inboundInterface(), "inboundInterface() should match");
        Assertions.assertEquals(expected.outboundInterface(), actual.outboundInterface(), "outboundInterface() should match");
        Assertions.assertEquals(expected.logAction(), actual.logAction(), "logAction() should match");
        Assertions.assertEquals(expected.sessionId(), actual.sessionId(), "sessionId() should match");
        Assertions.assertEquals(expected.repeatCount(), actual.repeatCount(), "repeatCount() should match");
        Assertions.assertEquals(expected.sourcePort(), actual.sourcePort(), "sourcePort() should match");
        Assertions.assertEquals(expected.destinationPort(), actual.destinationPort(), "destinationPort() should match");
        Assertions.assertEquals(expected.natSourcePort(), actual.natSourcePort(), "natSourcePort() should match");
        Assertions.assertEquals(expected.natDestinationPort(), actual.natDestinationPort(), "natDestinationPort() should match");
        Assertions.assertEquals(expected.flags(), actual.flags(), "flags() should match");
        Assertions.assertEquals(expected.protocol(), actual.protocol(), "protocol() should match");
        Assertions.assertEquals(expected.action(), actual.action(), "action() should match");
        Assertions.assertEquals(expected.bytes(), actual.bytes(), "bytes() should match");
        Assertions.assertEquals(expected.bytesSent(), actual.bytesSent(), "bytesSent() should match");
        Assertions.assertEquals(expected.bytesReceived(), actual.bytesReceived(), "bytesReceived() should match");
        Assertions.assertEquals(expected.packets(), actual.packets(), "packets() should match");
        Assertions.assertEquals(expected.startTime(), actual.startTime(), "startTime() should match");
        Assertions.assertEquals(expected.elapsedTime(), actual.elapsedTime(), "elapsedTime() should match");
        Assertions.assertEquals(expected.category(), actual.category(), "category() should match");
        Assertions.assertEquals(expected.sequenceNumber(), actual.sequenceNumber(), "sequenceNumber() should match");
        Assertions.assertEquals(expected.actionFlags(), actual.actionFlags(), "actionFlags() should match");
        Assertions.assertEquals(expected.sourceLocation(), actual.sourceLocation(), "sourceLocation() should match");
        Assertions.assertEquals(expected.destinationLocation(), actual.destinationLocation(), "destinationLocation() should match");
        Assertions.assertEquals(expected.packetsSent(), actual.packetsSent(), "packetsSent() should match");
        Assertions.assertEquals(expected.packetsReceived(), actual.packetsReceived(), "packetsReceived() should match");
        Assertions.assertEquals(expected.sessionEndReason(), actual.sessionEndReason(), "sessionEndReason() should match");
        Assertions.assertEquals(expected.deviceGroupHierarchyLevel1(), actual.deviceGroupHierarchyLevel1(), "deviceGroupHierarchyLevel1() should match");
        Assertions.assertEquals(expected.deviceGroupHierarchyLevel2(), actual.deviceGroupHierarchyLevel2(), "deviceGroupHierarchyLevel2() should match");
        Assertions.assertEquals(expected.deviceGroupHierarchyLevel3(), actual.deviceGroupHierarchyLevel3(), "deviceGroupHierarchyLevel3() should match");
        Assertions.assertEquals(expected.deviceGroupHierarchyLevel4(), actual.deviceGroupHierarchyLevel4(), "deviceGroupHierarchyLevel4() should match");
        Assertions.assertEquals(expected.virtualSystemName(), actual.virtualSystemName(), "virtualSystemName() should match");
        Assertions.assertEquals(expected.deviceName(), actual.deviceName(), "deviceName() should match");
        Assertions.assertEquals(expected.actionSource(), actual.actionSource(), "actionSource() should match");
        Assertions.assertEquals(expected.sourceVmUuid(), actual.sourceVmUuid(), "sourceVmUuid() should match");
        Assertions.assertEquals(expected.destinationVmUuid(), actual.destinationVmUuid(), "destinationVmUuid() should match");
        Assertions.assertEquals(expected.tunnelIdImsi(), actual.tunnelIdImsi(), "tunnelIdImsi() should match");
        Assertions.assertEquals(expected.monitorTagImei(), actual.monitorTagImei(), "monitorTagImei() should match");
        Assertions.assertEquals(expected.parentSessionId(), actual.parentSessionId(), "parentSessionId() should match");
        Assertions.assertEquals(expected.parentStartTime(), actual.parentStartTime(), "parentStartTime() should match");
        Assertions.assertEquals(expected.tunnelType(), actual.tunnelType(), "tunnelType() should match");
    }

}
