package mz.ukp;

import mz.ukp.io.Input;
import mz.ukp.io.InputProvider;
import mz.ukp.io.Output;
import mz.ukp.solve.BasicSpaceMinimizer;
import mz.ukp.solve.BruteForceSolver;
import mz.ukp.solve.NaiveOnPipesSolver;
import org.junit.Assert;
import org.junit.Test;

/**
 * Compares results to with a brute force.
 * 
 * @author stanislav.chizhov
 */
public class SolverVerifierTest {

    public static void main( String[] args )
    {
        RandomTestInputProvider p = new RandomTestInputProvider(new RandomTestInputProvider.MinMaxRandom(10, 100), new RandomTestInputProvider.MinMaxRandom(1, 30));
        for (int i=0;i<10000;i++) {
            System.out.println("============"+i+"=============");
            verifyAgainstBruteForce(p.getInput());
        }
    }
    
    private static void verifyAgainstBruteForce(Input i) {
        System.out.println(i);
        System.out.println("==========================");
        Solver s1 = new BasicSpaceMinimizer(new NaiveOnPipesSolver());
        Solver s2 = new BruteForceSolver();
        Output o1 = s1.solve(i);
        Output o2 = s2.solve(i);
        System.out.println("===============NaiveOnPipes:");
        o1.dump(System.out);
        System.out.println("===============BruteForce:");
        o2.dump(System.out);
        Assert.assertEquals(o1.getTotalPrice(), o2.getTotalPrice());
    }

    /**
     * TODO modify to make predictable
     */
    @Test
    public void shouldMatchBruteForce() {
        RandomTestInputProvider p = new RandomTestInputProvider(new RandomTestInputProvider.MinMaxRandom(2, 20), new RandomTestInputProvider.MinMaxRandom(2, 5));
        for (int i=0;i<100;i++) {
            System.out.println("============"+i+"=============");
            verifyAgainstBruteForce(p.getInput());
        }
    }
}
