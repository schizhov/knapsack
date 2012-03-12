package mz.ukp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mz.ukp.io.Campaign;
import mz.ukp.io.Input;
import mz.ukp.io.InputProvider;

/**
 *
 * @author stanislav.chizhov
 */
public class RandomTestInputProvider implements InputProvider {

    private final RandomGenerator capacity;
    private final RandomGenerator numberOfCampaigns;

    public RandomTestInputProvider(RandomGenerator capacity, RandomGenerator numberOfCampaigns) {
        this.capacity = capacity;
        this.numberOfCampaigns = numberOfCampaigns;
    }

    public Input getInput() {
        int c = capacity.next();
        int n = numberOfCampaigns.next();
        CampaignGenerator cg = new CampaignGenerator(new MinMaxRandom(1, c), new MinMaxRandom(1, c));
        List<Campaign> campaigns = new ArrayList<Campaign>(n);
        for (int i = 0; i < n; i++) {
            campaigns.add(cg.create());
        }
        return new Input(c, campaigns);
    }

    private static class CampaignGenerator {

        private final RandomGenerator sizeGen;
        private final RandomGenerator priceGen;
        int number;

        public CampaignGenerator(RandomGenerator size, RandomGenerator price) {
            this.sizeGen = size;
            this.priceGen = price;
        }

        public Campaign create() {
            return new Campaign("" + number++, sizeGen.next(), priceGen.next());
        }
    }

    public static class MinMaxRandom implements RandomGenerator {

        private final int min;
        private final int max;
        private final Random gen = new Random();

        public MinMaxRandom(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int next() {
            return gen.nextInt(max - min) + min;
        }
    }
}
