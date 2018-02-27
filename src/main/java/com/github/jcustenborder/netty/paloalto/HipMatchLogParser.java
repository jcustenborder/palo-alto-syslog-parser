
package com.github.jcustenborder.netty.paloalto;

import com.github.jcustenborder.netty.paloalto.ImmutableHipMatchLogMessage.Builder;
import com.github.jcustenborder.netty.syslog.RFC3164Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HipMatchLogParser
    extends PaloAltoParser<HipMatchLogMessage>
{

    private final static Logger log = LoggerFactory.getLogger(HipMatchLogParser.class);

    @Override
    public HipMatchLogMessage parse(RFC3164Message message, String[] fields) {
        Builder builder = ImmutableHipMatchLogMessage.builder();
        builder.from(message);
        log.trace("parse() - Skipping field 0: futureUse");
        log.trace("parse() - Processing field 1: receiveTime");
        builder.receiveTime(parseDate(fields, 1));
        log.trace("parse() - Processing field 2: serialNumber");
        builder.serialNumber(parseString(fields, 2));
        log.trace("parse() - Processing field 3: type");
        builder.type(parseString(fields, 3));
        log.trace("parse() - Skipping field 4: futureUse");
        log.trace("parse() - Skipping field 5: futureUse");
        log.trace("parse() - Processing field 6: generatedTime");
        builder.generatedTime(parseDate(fields, 6));
        log.trace("parse() - Processing field 7: sourceUser");
        builder.sourceUser(parseString(fields, 7));
        log.trace("parse() - Processing field 8: virtualSystem");
        builder.virtualSystem(parseString(fields, 8));
        log.trace("parse() - Processing field 9: machineName");
        builder.machineName(parseString(fields, 9));
        log.trace("parse() - Processing field 10: os");
        builder.os(parseString(fields, 10));
        log.trace("parse() - Processing field 11: sourceIp");
        builder.sourceIp(parseInetAddress(fields, 11));
        log.trace("parse() - Processing field 12: hip");
        builder.hip(parseString(fields, 12));
        log.trace("parse() - Processing field 13: repeatCount");
        builder.repeatCount(parseLong(fields, 13));
        log.trace("parse() - Processing field 14: hipType");
        builder.hipType(parseString(fields, 14));
        log.trace("parse() - Skipping field 15: futureUse");
        log.trace("parse() - Skipping field 16: futureUse");
        log.trace("parse() - Processing field 17: sequenceNumber");
        builder.sequenceNumber(parseLong(fields, 17));
        log.trace("parse() - Processing field 18: actionFlags");
        builder.actionFlags(parseInteger(fields, 18));
        log.trace("parse() - Processing field 19: deviceGroupHierarchyLevel1");
        builder.deviceGroupHierarchyLevel1(parseString(fields, 19));
        log.trace("parse() - Processing field 20: deviceGroupHierarchyLevel2");
        builder.deviceGroupHierarchyLevel2(parseString(fields, 20));
        log.trace("parse() - Processing field 21: deviceGroupHierarchyLevel3");
        builder.deviceGroupHierarchyLevel3(parseString(fields, 21));
        log.trace("parse() - Processing field 22: deviceGroupHierarchyLevel4");
        builder.deviceGroupHierarchyLevel4(parseString(fields, 22));
        log.trace("parse() - Processing field 23: virtualSystemName");
        builder.virtualSystemName(parseString(fields, 23));
        log.trace("parse() - Processing field 24: deviceName");
        builder.deviceName(parseString(fields, 24));
        log.trace("parse() - Processing field 25: virtualSystemId");
        builder.virtualSystemId(parseString(fields, 25));
        log.trace("parse() - Processing field 26: ipv6SourceIp");
        builder.ipv6SourceIp(parseString(fields, 26));
        return builder.build();
    }

    @Override
    public String logType() {
        return "HIP-MATCH";
    }

}