package mz.ukp;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;
import mz.ukp.enhance.BasicPruner;
import mz.ukp.enhance.GCDEnhancer;
import mz.ukp.io.Input;
import mz.ukp.io.Output;
import mz.ukp.io.impl.FileInputProvider;
import mz.ukp.solve.BasicSpaceMinimizer;
import mz.ukp.solve.NaiveOnPipesSolver;
import org.apache.commons.lang.UnhandledException;

/**
 * Accepts file name as an argument and solves given problem.
 *
 */
public class App {

    
    public static void main(String[] args)  {
        initLog("ukp.log");
        Input i = new FileInputProvider(new File(args[0])).getInput();
        Solver s = new BasicSpaceMinimizer(new NaiveOnPipesSolver());
        Output o = s.solve(i);
        o.dump(System.out);
    }
    
    private static void initLog(String file) {        
        try {
            for (Handler h: Logger.getLogger("").getHandlers()) {
                h.setLevel(Level.OFF);
            }
            Handler fh = new FileHandler(file, 100000, 1);
            fh.setFormatter(new SimpleFormatter());
            Logger.getLogger("").addHandler(fh);
        } catch (IOException ex) {
            throw new UnhandledException("Could not init log to the "+file, ex);
        } 
        
    }
}
