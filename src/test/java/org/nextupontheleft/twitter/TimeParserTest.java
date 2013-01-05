package org.nextupontheleft.twitter;

import org.joda.time.LocalTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: jcowie
 * Date: 03/10/2012
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class TimeParserTest {

    private TimeParser t = new TimeParser();

    @Test
    public void testAm() throws TweetParsingException {
        System.out.println("testAm");
        assertEquals(new LocalTime(10, 0), t.parse("10am"));
    }
    @Test
    public void testAmWithMinutes() throws TweetParsingException {
        System.out.println("testAmWithMinutes");
        assertEquals(new LocalTime(10, 30), t.parse("10:30am"));
    }
    @Test
    public void testPm() throws TweetParsingException {
        System.out.println("testPm");
        assertEquals(new LocalTime(16, 00), t.parse("4pm"));
    }
    @Test
    public void testPmWithMinutes() throws TweetParsingException {
        System.out.println("testPmWithMinutes");
        assertEquals(new LocalTime(16, 57), t.parse("4:57pm"));
    }
    @Test
    public void test24Hour() throws TweetParsingException {
        System.out.println("test24Hour");
        assertEquals(new LocalTime(15, 03), t.parse("1503"));
    }
    @Test
    public void test24HourFormatted() throws TweetParsingException {
        System.out.println("test24Formatted");
        assertEquals(new LocalTime(9, 43), t.parse("09:43"));
    }

}
