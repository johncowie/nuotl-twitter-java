package org.nextupontheleft.twitter;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: jcowie
 * Date: 03/10/2012
 * Time: 09:25
 * To change this template use File | Settings | File Templates.
 */
public class DurationParserTest {

    private DurationParser p = new DurationParser();

    @Test
    public void testMinutesValid() throws TweetParsingException {
        assertEquals(30, p.getDuration("30m"));
        assertEquals(181, p.getDuration("180.7M"));
    }

    @Test()
    public void testMinutesNegative() {
        try {
            p.getDuration("-15m");
        } catch(TweetParsingException e) {
            assertEquals(TweetParsingErrorCode.DURATION_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testHoursValid() throws TweetParsingException {
        assertEquals(2*60, p.getDuration("2h"));
        assertEquals(15, p.getDuration("0.25H"));
    }

    @Test
    public void testDaysValid() throws TweetParsingException {
        assertEquals(24*60, p.getDuration("1D"));
        assertEquals(36*60, p.getDuration("1.5d"));
    }
}
