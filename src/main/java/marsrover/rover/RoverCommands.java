package marsrover.rover;

import java.util.List;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;

public class RoverCommands {

    private final List<RoverDirectionalCommand> roverDirectionalCommands;

    public RoverCommands(List<RoverDirectionalCommand> roverDirectionalCommands) {
        this.roverDirectionalCommands = roverDirectionalCommands;
    }

    public List<RoverDirectionalCommand> getRoverDirectionalCommands() {
        return roverDirectionalCommands;
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
        return reflectionToString(this, SIMPLE_STYLE);
    }
}
