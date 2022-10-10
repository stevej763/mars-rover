package marsrover.rover;

import marsrover.control.RoverCompassDirection;
import marsrover.control.RoverCoordinates;
import marsrover.control.RoverLocationData;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoverLocationDataServiceTest {

    @Test
    public void canProvideDefaultCurrentLocation() {
        RoverLocationData startingLocation = new RoverLocationData(new RoverCoordinates(0, 0), RoverCompassDirection.NORTH);

        RoverLocationDataService underTest = new RoverLocationDataService(startingLocation);

        assertThat(underTest.getCurrentLocation(), is(startingLocation));
    }

    @Test
    public void canUpdateLocationData() {
        RoverLocationData startingLocation = new RoverLocationData(new RoverCoordinates(0, 0), RoverCompassDirection.NORTH);

        RoverLocationDataService underTest = new RoverLocationDataService(startingLocation);

        RoverLocationData nextLocation = new RoverLocationData(new RoverCoordinates(400, 200), RoverCompassDirection.SOUTH);
        underTest.updatePosition(nextLocation);

        assertThat(underTest.getCurrentLocation(), is(nextLocation));
    }
}