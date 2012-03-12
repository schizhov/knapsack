package mz.ukp.io;

import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *
 * @author stanislav.chizhov
 */
public class Input {

    private final int capacity;
    private final List<Campaign> campaigns;

    public Input(int capacity, List<Campaign> campaigns) {
        this.capacity = capacity;
        this.campaigns = campaigns;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public int getCapacity() {
        return capacity;
    }

    public int[] sizes(boolean withCapacity) {
        int[] sizes = new int[campaigns.size() + (withCapacity ? 1 : 0)];
        int i = 0;
        for (Campaign c : campaigns) {
            sizes[i++] = c.getSize();
        }
        if (withCapacity) {
            sizes[sizes.length - 1] = capacity;
        }
        return sizes;
    }

    public int[] prices() {
        int[] prices = new int[campaigns.size()];
        int i = 0;
        for (Campaign c : campaigns) {
            prices[i++] = c.getPrice();
        }

        return prices;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(capacity).append(campaigns).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Input other = (Input) obj;
        return new EqualsBuilder().append(capacity, other.capacity).append(campaigns, other.campaigns).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(capacity).append(campaigns).toHashCode();
    }
    
    
    
}
