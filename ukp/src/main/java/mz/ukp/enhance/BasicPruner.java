package mz.ukp.enhance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import mz.ukp.io.Campaign;
import mz.ukp.io.Input;
import mz.ukp.io.InputProvider;

/**
 *
 * @author stanislav.chizhov
 */
public class BasicPruner implements InputProvider {

    private final Logger LOG = Logger.getLogger(BasicPruner.class.getSimpleName());
    private Input p;

    public BasicPruner(Input p) {
        this.p = p;
    }

    public Input getInput() {
        List<Campaign> campaigns = new ArrayList<Campaign>(p.getCampaigns());
        for (Iterator<Campaign> i = campaigns.iterator(); i.hasNext();) {
            Campaign c = i.next();
            if (c.getSize() > p.getCapacity()) {
                LOG.info("Removing "+c);
                i.remove();
                
            }
        }
        return new Input(p.getCapacity(), campaigns);
    }
}
