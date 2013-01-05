package org.nextupontheleft.twitter;

import org.junit.Test;
import org.nextupontheleft.domain.Area;
import org.nextupontheleft.domain.Region;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: jcowie
 * Date: 05/10/2012
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public class AreaParserTest {

    private AreaParser p = new AreaParser();
    private static final Region UK = new Region("R", "UK");
    private static final Area LONDON = new Area("L", "London", UK);

    @Test
    public void testAddArea() throws TweetParsingException{
        p.addArea(LONDON);
        Area a = p.parse("L");
        assertEquals(LONDON, a);
    }

    @Test
    public void testNonExistentArea() {
        try {
            Area a = p.parse("L");
            fail();
        } catch(TweetParsingException e) {
            assertEquals(TweetParsingErrorCode.POSTAL_AREA_ERROR, e.getErrorCode());
        }
    }
}
