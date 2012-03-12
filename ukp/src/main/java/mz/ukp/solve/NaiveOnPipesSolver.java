package mz.ukp.solve;

import java.util.Arrays;
import java.util.logging.Logger;
import mz.ukp.Solver;
import mz.ukp.io.Input;
import mz.ukp.io.Output;

/**
 * Dynamic programming solver with list per type to minimize memory consumption.
 * Based on a recursion <tt>c(i,k)=max(c(i,k-1),c(i-size(k))+price(k))</tt> for
 * the cost of optimal solution for capacity c and first k campaign types. Time
 * complexity is <tt>O(C*N)</tt> where C = total capacity and N = number of
 * different campaign types. Space complexity is <tt>O(W*N)</tt> where W =
 * average campaign weight and N = number of different campaign types
 *
 * TODO: add dominance pruning
 *
 * @author stanislav.chizhov
 */
public class NaiveOnPipesSolver implements Solver {

    private final static Logger LOG = Logger.getLogger(NaiveOnPipesSolver.class.getSimpleName());
    private State state;

    public NaiveOnPipesSolver() {
    }

    /**
     * Computes recursion <tt>c(i,k)=max(c(i,k-1),c(i-size(k))+price(k))</tt>
     *
     * @param i capacity
     * @param k number of campaign types
     * @return cost for optimal solution
     */
    private int computeCost(int i, int k) {
        int previous = k - 1;
        return Math.max(previous < 0 ? 0 : pipe(previous).head(), pipe(k).tail() + price(k));
    }

    private static int leftmost(int[] values, int v) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == v) {
                return i;
            }
        }
        throw new IllegalArgumentException("Could not find value " + v + " in row " + Arrays.toString(values));
    }

    /**
     * Computes maximal cost and stores recursion path for the optimal solution
     * in <tt>item</tt> and <tt>cost</tt> arrays.
     */
    private void computeCosts() {
        int[] slice = new int[state.types];
        int max;
        for (int i = 0; i <= state.capacity; i++) {
            max = 0;
            for (int k = 0; k < state.types; k++) {
                slice[k] = computeCost(i, k);
                pipe(k).push(slice[k]);
                if (slice[k] > max) {
                    max = slice[k];
                }
            }
            state.storeSolution(slice, max, i);
        }
    }

    public Output solve(Input in) {
        LOG.info("Solving " + in);
        state = new State(in);
        computeCosts();
        state.computeCounts();
        return Output.create(in, state.count);

    }
    
    private int price(int k) {
        return state.price[k];
    }
    
    private Pipe pipe(int k) {
        return state.pipes[k];
    }

    private static class State {

        private final Input input;
        /**
         * Campaign sizes for each campaign.
         */
        private final int size[];
        /**
         * Campaign prices for each campaign.
         */
        private final int price[];
        /**
         * Campaign indexes that form optimal combination for . Size = capacity.
         * Value at index <tt>i</tt> contains last campaign in the optimal
         * solution for the capacity i.
         */
        private final int item[];
        /**
         * Costs of optimal solutions for each capacity.
         */
        private final int cost[];
        /**
         * Final counts for each campaign in the optimal solution.
         */
        private final int count[];
        /**
         * Each i-th <tt>Pipe</tt> stores last <tt>size[i]</tt> prices for
         * recursion <tt>c(i,k)=max(c(i,k-1),c(i-size(k))+price(k))</tt>
         */
        private final Pipe[] pipes;
        private final int capacity;
        private final int types;

        public State(Input input) {
            this.input = input;
            capacity = input.getCapacity();
            size = input.sizes(false);
            types = size.length;
            price = input.prices();
            count = new int[types];
            item = new int[capacity + 1];
            cost = new int[capacity + 1];
            Arrays.fill(count, 0);
            
            pipes = new Pipe[types];
            for (int i = 0; i < types; i++) {
                pipes[i] = new ArrayPipe(size[i]);
            }
        }

        private void computeCounts() {
            int i = capacity;
            while (cost[i] > 0) {
                count[item[i]]++;
                i -= size[item[i]];
            }
        }
        
        private void storeSolution(int[] slice,int max, int i) {
            item[i] = leftmost(slice, max);
            cost[i] = max;
        }
    }
}
