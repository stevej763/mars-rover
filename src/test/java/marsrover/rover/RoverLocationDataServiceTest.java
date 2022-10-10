package marsrover.rover;

import marsrover.control.RoverCompassDirection;
import marsrover.control.RoverCoordinates;
import marsrover.control.RoverLocationData;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RoverLocationDataServiceTest {

    private final RoverLocationMessageSender roverLocationMessageSender = mock(RoverLocationMessageSender.class);

    @Test
    public void canProvideDefaultCurrentLocation() {
        RoverLocationData startingLocation = new RoverLocationData(new RoverCoordinates(0, 0), RoverCompassDirection.NORTH);

        RoverLocationDataService underTest = new RoverLocationDataService(startingLocation, roverLocationMessageSender);

        assertThat(underTest.getCurrentLocation(), is(startingLocation));
    }

    @Test
    public void canUpdateLocationData() {
        RoverLocationData startingLocation = new RoverLocationData(new RoverCoordinates(0, 0), RoverCompassDirection.NORTH);

        RoverLocationDataService underTest = new RoverLocationDataService(startingLocation, roverLocationMessageSender);

        RoverLocationData nextLocation = new RoverLocationData(new RoverCoordinates(400, 200), RoverCompassDirection.SOUTH);
        RoverLocationUpdate locationUpdate = new RoverLocationUpdate(nextLocation);

        underTest.updatePosition(locationUpdate);

        RoverLocationMessageDto roverMessageDto = new RoverLocationMessageDto(nextLocation.getRoverCoordinates(), nextLocation.getRoverCompassDirection());
        verify(roverLocationMessageSender).send(roverMessageDto);
        assertThat(underTest.getCurrentLocation(), is(nextLocation));
    }
}