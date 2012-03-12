package mz.ukp.solve;

import java.util.Arrays;
import org.apache.commons.lang.Validate;

/**
 *
 * Holds maximum  <tt>size</tt> number of elements.
 * First and last can be accessed.
 * 
 * @author stanislav.chizhov
 */
public class ArrayPipe implements Pipe {

    private final int[] values;
    private int idx;
    private int size;
    private boolean full;

    public ArrayPipe(int size) {
        Validate.isTrue(size > 0);
        this.size = size;
        values = new int[size];
        Arrays.fill(values, MINUS_INFINITY);
    }

    public int push(int v) {
        if (full) {
            return pushIfFull(v);
        }
        pushIfNotFull(v);

        return MINUS_INFINITY;
    }

    private void store(int v) {
        values[idx++] = v;
    }

    private void pushIfNotFull(int v) {
        store(v);
        if (idx == size) {
            idx = 0;
            full = true;
        }
    }

    private int pushIfFull(int v) {
        int ret = values[idx];
        store(v);
        idx = idx % size;
        return ret;
    }

    public int head() {
        return values[(size + idx - 1) % size];
    }

    public int tail() {
        return values[idx];
    }
}
