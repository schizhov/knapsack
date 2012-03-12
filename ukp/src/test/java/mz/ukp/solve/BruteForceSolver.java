package mz.ukp.solve;

import java.util.Arrays;
import mz.ukp.Solver;
import mz.ukp.io.Input;
import mz.ukp.io.Output;

/**
 * For testing purposes only
 *
 * @author stanislav.chizhov
 */
public class BruteForceSolver implements Solver {

    private State state;

    public Output solve(Input in) {
        state = new State(in);
        state.compute(0);
        System.out.println("price = " + state.max);
        return Output.create(state.input, state.best);
    }

    private static class State {

        private final int capacity;
        private final int w[];
        private final int p[];
        private final int count[];
        private int best[];
        private int max;
        private int current;
        private int price;
        private final Input input;

        public State(Input input) {
            this.input = input;
            capacity = input.getCapacity();
            w = input.sizes(false);
            p = input.prices();
            count = new int[w.length];
            Arrays.fill(count, 0);
        }

        public void compute(int idx) {
            if (idx == w.length) {
                return;
            }

            while (current <= capacity) {

                if (price > max) {
                    max = price;
                    best = Arrays.copyOf(count, count.length);
                    //System.out.println("==================Found best at "+idx+"with price"+max+" "+Arrays.toString(best));
                }
                compute(idx + 1);
                count[idx]++;
                current += w[idx];
                price += p[idx];
                //System.out.println("Processing "+w[idx]+" with price "+price+" and current "+current+" "+Arrays.toString(count));
            }
            current -= w[idx] * count[idx];
            price -= p[idx] * count[idx];
            count[idx] = 0;
        }
    }
}
