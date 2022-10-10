package marsrover.rover;

import marsrover.control.RoverCompassDirection;
import marsrover.control.RoverCoordinates;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;

public class RoverLocationMessageDto {
    private final RoverCoordinates roverCoordinates;
    private final RoverCompassDirection roverCompassDirection;

    public RoverLocationMessageDto(RoverCoordinates roverCoordinates, RoverCompassDirection roverCompassDirection) {
        this.roverCoordinates = roverCoordinates;
        this.roverCompassDirection = roverCompassDirection;
    }

    public RoverCoordinates getRoverCoordinates() {
        return roverCoordinates;
    }

    public RoverCompassDirection getRoverCompassDirection() {
        return roverCompassDirection;
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
