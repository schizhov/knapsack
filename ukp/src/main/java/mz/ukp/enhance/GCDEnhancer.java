package mz.ukp.enhance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import mz.ukp.io.Campaign;
import mz.ukp.io.Input;
import mz.ukp.io.InputProvider;
import mz.ukp.io.Output;
import mz.ukp.util.GCD;
import org.apache.commons.lang.Validate;


/**
 * Divide all sizes by GCD to minimize search space.
 * Able multiply <tt>Output</tt> to get correct results at the end.
 * 
 * @author stanislav.chizhov
 */ 
public class GCDEnhancer implements InputProvider {

    private final Logger LOG = Logger.getLogger(GCDEnhancer.class.getSimpleName());
    
    private final InputProvider provider;
    private int gcd = -1;

    public GCDEnhancer(InputProvider provider) {
        Validate.notNull(provider);
        this.provider = provider;
    }

    public Output transform(Output output) {
        if (gcd < 0) {
            throw new IllegalStateException("Invoke getInput() first");
        }

        Map<Campaign, Integer> counts = output.getCounts();
        Map<Campaign, Integer> modified = new HashMap<Campaign, Integer>(counts.size());
        for (Entry<Campaign, Integer> e : counts.entrySet()) {
            modified.put(e.getKey().multiplySize(gcd), e.getValue());
        }
        return new Output(modified);
    }

    public Input getInput() {
        Input i = provider.getInput();
        gcd = GCD.gcd(i.sizes(true));
        LOG.info("GCD="+gcd);
        List<Campaign> campaigns = i.getCampaigns();
        List<Campaign> modified = new ArrayList<Campaign>(campaigns.size());
        for (Campaign c : campaigns) {
            modified.add(new Campaign(c.getName(), c.getSize() / gcd, c.getPrice()));
        }
        Input r = new Input(i.getCapacity() / gcd, modified);
        return r;
    }
}
