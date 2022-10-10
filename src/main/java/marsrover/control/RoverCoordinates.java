package marsrover.control;

import org.apache.commons.lang3.builder.ToStringStyle;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class RoverCoordinates {
    private final Integer x;
    private final Integer y;

    public RoverCoordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj, true);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this, true);
    }

    @Override
    public String toString() {
        return reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
