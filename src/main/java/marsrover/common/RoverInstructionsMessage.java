package marsrover.common;

import java.util.List;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;

public class RoverInstructionsMessage {

    private final List<RoverCommand> commands;

    public RoverInstructionsMessage(List<RoverCommand> commands) {
        this.commands = commands;
    }

    public List<RoverCommand> getCommands() {
        return commands;
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
