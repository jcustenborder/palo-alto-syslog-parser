
package com.github.jcustenborder.netty.paloalto;

import com.github.jcustenborder.netty.paloalto.ImmutableUserIdLogMessage.Builder;
import com.github.jcustenborder.netty.syslog.RFC3164Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserIdLogParser
    extends PaloAltoParser<UserIdLogMessage>
{

    private final static Logger log = LoggerFactory.getLogger(UserIdLogParser.class);

    @Override
    public UserIdLogMessage parse(RFC3164Message message, String[] fields) {
        Builder builder = ImmutableUserIdLogMessage.builder();
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
        return builder.build();
    }

    @Override
    public String logType() {
        return "USERID";
    }

}
