package org.nextupontheleft.twitter;

import org.junit.Test;

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

    @Test
    public void testAddArea() throws TweetParsingException{
        p.addArea("l");
        String a = p.parse("L");
        assertEquals("L", a);
    }

    @Test
    public void testNonExistentArea() {
        try {
            String a = p.parse("L");
            fail();
        } catch(TweetParsingException e) {
            assertEquals(TweetParsingErrorCode.POSTAL_AREA_ERROR, e.getErrorCode());
        }
    }
}
