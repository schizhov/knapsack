package mz.ukp.io;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author stanislav.chizhov
 */
public class Campaign {

    private final String name;
    private final int size;
    private final int price;

    public Campaign(String name, int size, int price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public Campaign multiplySize(int n) {
        return new Campaign(name, size * n, price);
    }

    public Campaign divideSize(int n) {
        return new Campaign(name, size / n, price);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(name).append("size",size).append("price",price).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Campaign other = (Campaign) obj;
        return new EqualsBuilder().append(name, other.name).append(size, other.size).append(price,other.price).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(size).append(price).toHashCode();
    }
    
    
}
