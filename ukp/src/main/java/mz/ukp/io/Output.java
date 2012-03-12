package mz.ukp.io;

import java.io.PrintStream;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Solution: campaign distribution.
 * 
 * @author stanislav.chizhov
 */
public class Output {

    private final Map<Campaign, Integer> counts;

    public Output(Map<Campaign, Integer> counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(counts).toString();
    }

    public Map<Campaign, Integer> getCounts() {
        return Collections.unmodifiableMap(counts);
    }

    public int getTotalSize() {
        int size = 0;
        for (Map.Entry<Campaign, Integer> e : counts.entrySet()) {
            Campaign c = e.getKey();

            size += e.getKey().getSize() * e.getValue();
        }
        return size;
    }

    public int getTotalPrice() {
        int size = 0;
        for (Map.Entry<Campaign, Integer> e : counts.entrySet()) {
            size += e.getKey().getPrice() * e.getValue();
        }
        return size;
    }

    public void dump(PrintStream out) {
        int totalSize = 0;
        int totalPrice = 0;
        for (Map.Entry<Campaign, Integer> e : counts.entrySet()) {
            Campaign c = e.getKey();
            int count = e.getValue();
            int price = c.getPrice() * count;
            int size = c.getSize() * count;

            out.println(StringUtils.join(asList(c.getName(), count, size, price), ","));

            totalPrice += price;
            totalSize += size;
        }
        out.println(StringUtils.join(asList(totalSize, totalPrice), ","));
    }

    public static Output create(Input input, int[] count) {
        Map<Campaign, Integer> campaignCounts = new HashMap<Campaign, Integer>();
        List<Campaign> campaigns = input.getCampaigns();
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                campaignCounts.put(campaigns.get(i), count[i]);
            }
        }
        return new Output(campaignCounts);

    }
}
