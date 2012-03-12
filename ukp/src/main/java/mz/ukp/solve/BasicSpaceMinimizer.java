package mz.ukp.solve;

import mz.ukp.Solver;
import mz.ukp.enhance.BasicPruner;
import mz.ukp.enhance.GCDEnhancer;
import mz.ukp.io.Input;
import mz.ukp.io.Output;

/**
 *
 * @author stanislav.chizhov
 */
public class BasicSpaceMinimizer implements Solver {

    private final Solver solver;

    public BasicSpaceMinimizer(Solver solver) {
        this.solver = solver;
    }
    
    public Output solve(Input i) {
        GCDEnhancer minimizer =  new GCDEnhancer(new BasicPruner(i));
        Output o = solver.solve(minimizer.getInput());
        return minimizer.transform(o);
    }
    
}
