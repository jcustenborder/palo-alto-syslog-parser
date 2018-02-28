
package com.github.jcustenborder.netty.paloalto;

import com.github.jcustenborder.netty.paloalto.ImmutableConfigLogMessage.Builder;
import com.github.jcustenborder.netty.syslog.RFC3164Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLogParser
    extends PaloAltoParser<ConfigLogMessage>
{

    private final static Logger log = LoggerFactory.getLogger(ConfigLogParser.class);

    @Override
    public ConfigLogMessage parse(RFC3164Message message, String[] fields) {
        Builder builder = ImmutableConfigLogMessage.builder();
        builder.from(message);
        log.trace("parse() - Skipping field 0: futureUse");
        log.trace("parse() - Processing field 1: receiveTime");
        builder.receiveTime(parseDate(fields, 1));
        log.trace("parse() - Processing field 2: serialNumber");
        builder.serialNumber(parseString(fields, 2));
        log.trace("parse() - Processing field 3: type");
        builder.type(parseString(fields, 3));
        log.trace("parse() - Processing field 4: subtype");
        builder.subtype(parseString(fields, 4));
        log.trace("parse() - Skipping field 5: futureUse");
        log.trace("parse() - Processing field 6: generatedTime");
        builder.generatedTime(parseDate(fields, 6));
        log.trace("parse() - Processing field 7: host");
        builder.host(parseString(fields, 7));
        log.trace("parse() - Processing field 8: virtualSystem");
        builder.virtualSystem(parseString(fields, 8));
        log.trace("parse() - Processing field 9: command");
        builder.command(parseString(fields, 9));
        log.trace("parse() - Processing field 10: admin");
        builder.admin(parseString(fields, 10));
        log.trace("parse() - Processing field 11: client");
        builder.client(parseString(fields, 11));
        log.trace("parse() - Processing field 12: result");
        builder.result(parseString(fields, 12));
        log.trace("parse() - Processing field 13: configurationPath");
        builder.configurationPath(parseString(fields, 13));
        log.trace("parse() - Processing field 14: beforeChangeDetail");
        builder.beforeChangeDetail(parseString(fields, 14));
        log.trace("parse() - Processing field 15: afterChangeDetail");
        builder.afterChangeDetail(parseString(fields, 15));
        log.trace("parse() - Processing field 16: sequenceNumber");
        builder.sequenceNumber(parseLong(fields, 16));
        log.trace("parse() - Processing field 17: actionFlags");
        builder.actionFlags(parseString(fields, 17));
        log.trace("parse() - Processing field 18: deviceGroupHierarchyLevel1");
        builder.deviceGroupHierarchyLevel1(parseString(fields, 18));
        log.trace("parse() - Processing field 19: deviceGroupHierarchyLevel2");
        builder.deviceGroupHierarchyLevel2(parseString(fields, 19));
        log.trace("parse() - Processing field 20: deviceGroupHierarchyLevel3");
        builder.deviceGroupHierarchyLevel3(parseString(fields, 20));
        log.trace("parse() - Processing field 21: deviceGroupHierarchyLevel4");
        builder.deviceGroupHierarchyLevel4(parseString(fields, 21));
        log.trace("parse() - Processing field 22: virtualSystemName");
        builder.virtualSystemName(parseString(fields, 22));
        log.trace("parse() - Processing field 23: deviceName");
        builder.deviceName(parseString(fields, 23));
        return builder.build();
    }

    @Override
    public String logType() {
        return "CONFIG";
    }

}
