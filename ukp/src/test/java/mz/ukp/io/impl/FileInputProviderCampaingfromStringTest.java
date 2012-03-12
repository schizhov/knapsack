package mz.ukp.io.impl;

import mz.ukp.io.Campaign;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author stanislav.chizhov
 */
public class FileInputProviderCampaingfromStringTest {

    @Test(expected = IllegalArgumentException.class)
    public void failsInCase2Tokens() {
        FileInputProvider.fromString("a,3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsInCase4Tokens() {
        FileInputProvider.fromString("a,3,5,6");
    }

    @Test
    public void createdFromCorrectString() {
        assertEquals(new Campaign("a", 3, 5), FileInputProvider.fromString("a,3,5"));
    }

    @Test
    public void trimsSpacesInTheName() {
        assertEquals(new Campaign("a b", 3, 5), FileInputProvider.fromString(" a b ,3,5"));
    }

    @Test
    public void trimSpacesInValues() {
        assertEquals(new Campaign("a b", 3, 5), FileInputProvider.fromString(" a b , 3 , 5 "));
    }
}
