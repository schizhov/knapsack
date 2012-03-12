package mz.ukp.solve;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author stanislav.chizhov
 */
public class NewArrayPipeTest {
    
    private final Pipe pipe = new ArrayPipe(1);
    
    @Test(expected=IllegalArgumentException.class)
    public void cannotBeCreatedWithZeroSize() {
        new ArrayPipe(0);
    }
    
    @Test
    public void hasNothingToPushOut() {
        Assert.assertEquals(Pipe.MINUS_INFINITY, pipe.push(2));
    }
    
    @Test
    public void hasNoHead() {
        Assert.assertEquals(Pipe.MINUS_INFINITY, pipe.head());
    }
    
    @Test
    public void hasNoTail() {
        Assert.assertEquals(Pipe.MINUS_INFINITY, pipe.tail());
    }
    
    @Test
    public void hasHeadAfterPush() {
        pipe.push(2);
        Assert.assertEquals(2, pipe.head());
    }
    
    @Test
    public void hasTailIfFull() {
        pipe.push(2);
        Assert.assertEquals(2, pipe.tail());
    }
}
