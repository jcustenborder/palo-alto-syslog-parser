
package com.github.jcustenborder.netty.paloalto;

import com.github.jcustenborder.netty.paloalto.ImmutableTrafficLogMessage.Builder;
import com.github.jcustenborder.netty.syslog.RFC3164Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrafficLogParser
    extends PaloAltoParser<TrafficLogMessage>
{

    private final static Logger log = LoggerFactory.getLogger(TrafficLogParser.class);

    @Override
    public TrafficLogMessage parse(RFC3164Message message, String[] fields) {
        Builder builder = ImmutableTrafficLogMessage.builder();
        builder.from(message);
        log.trace("parse() - Skipping field 0: futureUse");
        log.trace("parse() - Processing field 1: receiveTime");
        builder.receiveTime(parseDate(fields, 1));
        log.trace("parse() - Processing field 2: serialNumber");
        builder.serialNumber(parseString(fields, 2));
        log.trace("parse() - Processing field 3: type");
        builder.type(parseString(fields, 3));
        log.trace("parse() - Processing field 4: threatContentType");
        builder.threatContentType(parseString(fields, 4));
        log.trace("parse() - Skipping field 5: futureUse");
        log.trace("parse() - Processing field 6: generatedTime");
        builder.generatedTime(parseDate(fields, 6));
        log.trace("parse() - Processing field 7: sourceIp");
        builder.sourceIp(parseInetAddress(fields, 7));
        log.trace("parse() - Processing field 8: destinationIp");
        builder.destinationIp(parseInetAddress(fields, 8));
        log.trace("parse() - Processing field 9: natSourceIp");
        builder.natSourceIp(parseInetAddress(fields, 9));
        log.trace("parse() - Processing field 10: natDestinationIp");
        builder.natDestinationIp(parseInetAddress(fields, 10));
        log.trace("parse() - Processing field 11: ruleName");
        builder.ruleName(parseString(fields, 11));
        log.trace("parse() - Processing field 12: sourceUser");
        builder.sourceUser(parseString(fields, 12));
        log.trace("parse() - Processing field 13: destinationUser");
        builder.destinationUser(parseString(fields, 13));
        log.trace("parse() - Processing field 14: application");
        builder.application(parseString(fields, 14));
        log.trace("parse() - Processing field 15: virtualSystem");
        builder.virtualSystem(parseString(fields, 15));
        log.trace("parse() - Processing field 16: sourceZone");
        builder.sourceZone(parseString(fields, 16));
        log.trace("parse() - Processing field 17: destinationZone");
        builder.destinationZone(parseString(fields, 17));
        log.trace("parse() - Processing field 18: inboundInterface");
        builder.inboundInterface(parseString(fields, 18));
        log.trace("parse() - Processing field 19: outboundInterface");
        builder.outboundInterface(parseString(fields, 19));
        log.trace("parse() - Processing field 20: logAction");
        builder.logAction(parseString(fields, 20));
        log.trace("parse() - Skipping field 21: futureUse");
        log.trace("parse() - Processing field 22: sessionId");
        builder.sessionId(parseLong(fields, 22));
        log.trace("parse() - Processing field 23: repeatCount");
        builder.repeatCount(parseLong(fields, 23));
        log.trace("parse() - Processing field 24: sourcePort");
        builder.sourcePort(parseInteger(fields, 24));
        log.trace("parse() - Processing field 25: destinationPort");
        builder.destinationPort(parseInteger(fields, 25));
        log.trace("parse() - Processing field 26: natSourcePort");
        builder.natSourcePort(parseInteger(fields, 26));
        log.trace("parse() - Processing field 27: natDestinationPort");
        builder.natDestinationPort(parseInteger(fields, 27));
        log.trace("parse() - Processing field 28: flags");
        builder.flags(parseInteger(fields, 28));
        log.trace("parse() - Processing field 29: protocol");
        builder.protocol(parseString(fields, 29));
        log.trace("parse() - Processing field 30: action");
        builder.action(parseString(fields, 30));
        log.trace("parse() - Processing field 31: bytes");
        builder.bytes(parseLong(fields, 31));
        log.trace("parse() - Processing field 32: bytesSent");
        builder.bytesSent(parseLong(fields, 32));
        log.trace("parse() - Processing field 33: bytesReceived");
        builder.bytesReceived(parseLong(fields, 33));
        log.trace("parse() - Processing field 34: packets");
        builder.packets(parseLong(fields, 34));
        log.trace("parse() - Processing field 35: startTime");
        builder.startTime(parseDate(fields, 35));
        log.trace("parse() - Processing field 36: elapsedTime");
        builder.elapsedTime(parseLong(fields, 36));
        log.trace("parse() - Processing field 37: category");
        builder.category(parseString(fields, 37));
        log.trace("parse() - Skipping field 38: futureUse");
        log.trace("parse() - Processing field 39: sequenceNumber");
        builder.sequenceNumber(parseLong(fields, 39));
        log.trace("parse() - Processing field 40: actionFlags");
        builder.actionFlags(parseInteger(fields, 40));
        log.trace("parse() - Processing field 41: sourceLocation");
        builder.sourceLocation(parseString(fields, 41));
        log.trace("parse() - Processing field 42: destinationLocation");
        builder.destinationLocation(parseString(fields, 42));
        log.trace("parse() - Skipping field 43: futureUse");
        log.trace("parse() - Processing field 44: packetsSent");
        builder.packetsSent(parseLong(fields, 44));
        log.trace("parse() - Processing field 45: packetsReceived");
        builder.packetsReceived(parseLong(fields, 45));
        log.trace("parse() - Processing field 46: sessionEndReason");
        builder.sessionEndReason(parseString(fields, 46));
        log.trace("parse() - Processing field 47: deviceGroupHierarchyLevel1");
        builder.deviceGroupHierarchyLevel1(parseString(fields, 47));
        log.trace("parse() - Processing field 48: deviceGroupHierarchyLevel2");
        builder.deviceGroupHierarchyLevel2(parseString(fields, 48));
        log.trace("parse() - Processing field 49: deviceGroupHierarchyLevel3");
        builder.deviceGroupHierarchyLevel3(parseString(fields, 49));
        log.trace("parse() - Processing field 50: deviceGroupHierarchyLevel4");
        builder.deviceGroupHierarchyLevel4(parseString(fields, 50));
        log.trace("parse() - Processing field 51: virtualSystemName");
        builder.virtualSystemName(parseString(fields, 51));
        log.trace("parse() - Processing field 52: deviceName");
        builder.deviceName(parseString(fields, 52));
        log.trace("parse() - Processing field 53: actionSource");
        builder.actionSource(parseString(fields, 53));
        log.trace("parse() - Processing field 54: sourceVmUuid");
        builder.sourceVmUuid(parseString(fields, 54));
        log.trace("parse() - Processing field 55: destinationVmUuid");
        builder.destinationVmUuid(parseString(fields, 55));
        log.trace("parse() - Processing field 56: tunnelIdImsi");
        builder.tunnelIdImsi(parseString(fields, 56));
        log.trace("parse() - Processing field 57: monitorTagImei");
        builder.monitorTagImei(parseString(fields, 57));
        log.trace("parse() - Processing field 58: parentSessionId");
        builder.parentSessionId(parseString(fields, 58));
        log.trace("parse() - Processing field 59: parentStartTime");
        builder.parentStartTime(parseDate(fields, 59));
        log.trace("parse() - Processing field 60: tunnelType");
        builder.tunnelType(parseString(fields, 60));
        return builder.build();
    }

    @Override
    public String logType() {
        return "TRAFFIC";
    }

}
