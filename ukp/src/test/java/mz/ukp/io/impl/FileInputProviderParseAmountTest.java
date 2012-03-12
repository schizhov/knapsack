package mz.ukp.io.impl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author stanislav.chizhov
 */
public class FileInputProviderParseAmountTest {
    
    @Test(expected=IllegalArgumentException.class)
    public void failsForNegaiveAmounts() {
        FileInputProvider.parseAmount("-1");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void failsForZeroAmounts() {
        FileInputProvider.parseAmount("0");
    }
    
    @Test
    public void worksForPositiveIntegers() {
        assertEquals(123,FileInputProvider.parseAmount("123"));
    }
    
    @Test
    public void trimsSpaces() {
        assertEquals(125,FileInputProvider.parseAmount(" 125 "));
    }
    
}
