package mz.ukp;

import mz.ukp.io.Input;
import mz.ukp.io.Output;

/**
 * Solves certain <tt>Input</tt>
 * 
 * @author stanislav.chizhov
 */
public interface Solver {

    public Output solve(Input i);
}
