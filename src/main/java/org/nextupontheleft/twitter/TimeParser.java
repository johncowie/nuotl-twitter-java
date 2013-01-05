package org.nextupontheleft.twitter;

import com.mdimension.jchronic.Chronic;
import com.mdimension.jchronic.utils.Span;
import org.joda.time.LocalTime;

/**
 *
 * @author john
 */
public class TimeParser {

    public LocalTime parse(String timeString) throws TweetParsingException {
        Span timeSpan = Chronic.parse(timeString);
        if(timeSpan != null) {
            return new LocalTime(timeSpan.getBeginCalendar().getTime());
        }
        throw new TweetParsingException(TweetParsingErrorCode.TIME_ERROR);
    }

}
