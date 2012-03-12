package mz.ukp.solve;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author stanislav.chizhov
 */
public class FullArrayPipeWith2ValuesTest {

    private final int size = 2;
    private final Pipe pipe = new ArrayPipe(size);

    @Before
    public void setUp() {
        pipe.push(1);
        pipe.push(2);
    }

    @Test
    public void has2ndAsHead() {
        Assert.assertEquals(2, pipe.head());
    }

    @Test
    public void has1stAsTail() {
        Assert.assertEquals(1, pipe.tail());
    }

    @Test
    public void shouldReturn1stValueAt3rdPush() {
        Assert.assertEquals(1, pipe.push(3));
    }

    @Test
    public void has3rdAsHeadAfter3rdPush() {
        pipe.push(3);
        Assert.assertEquals(3, pipe.head());
    }

    @Test
    public void has2ndAsTailAfter3rdPush() {
        pipe.push(3);
        Assert.assertEquals(2, pipe.tail());
    }

    @Test
    public void shouldReturn2ndValueAt4thrdPush() {
        pipe.push(3);
        Assert.assertEquals(2, pipe.push(4));
    }

    @Test
    public void shouldReturn3rdValueAt5thrdPush() {
        pipe.push(3);
        pipe.push(4);
        Assert.assertEquals(3, pipe.push(5));
    }
}
