package marsrover.rover;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class IllegalRoverDirectionalCommandException extends IllegalArgumentException {

    private final String command;

    public IllegalRoverDirectionalCommandException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        return String.format("Cannot parse instructions due to invalid command. command=%s", command);
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
