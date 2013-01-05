package org.nextupontheleft.twitter;

/**
 * Created with IntelliJ IDEA.
 * User: jcowie
 * Date: 03/10/2012
 * Time: 10:57
 * To change this template use File | Settings | File Templates.
 */
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DateParserTest {
    private DateParser p;
    private LocalDate testDate;

    @Before
    public void setup() {
        testDate = new LocalDate(2012, 3, 25);
        p = new DateParser(testDate); // set 'today' of parser as testDate
    }

    @Test
    public void testToday() throws TweetParsingException
    {
        assertEquals(testDate, p.parse("today"));
        assertEquals(testDate, p.parse("2day"));
    }

    @Test
    public void testTomorrow() throws TweetParsingException {
        assertEquals(testDate.plusDays(1), p.parse("tomorrow"));
        assertEquals(testDate.plusDays(1), p.parse("2moro"));
    }

    @Test
    public void testDate() throws TweetParsingException {
        assertEquals(new LocalDate(2013, DateTimeConstants.JUNE, 30), p.parse("30/06/2013"));
    }

    @Test
    public void testDayOfWeek() throws TweetParsingException {
        LocalDate d = p.parse("monday");
        assertEquals(DateTimeConstants.MONDAY, d.getDayOfWeek());
        d = p.parse("thu");
        assertEquals(DateTimeConstants.THURSDAY, d.getDayOfWeek());
    }


}
