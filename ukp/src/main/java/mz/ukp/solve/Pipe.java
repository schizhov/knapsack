package mz.ukp.solve;

/**
 * Holds limited number of values.
 * 
 * @author stanislav.chizhov
 */
public interface Pipe {
    
    public static final int MINUS_INFINITY = Integer.MIN_VALUE;

    public int push(int v);
    
    public int head();
    
    public int tail();
    
}
