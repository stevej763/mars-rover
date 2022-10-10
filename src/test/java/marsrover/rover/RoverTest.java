package marsrover.rover;

import marsrover.control.RoverCoordinates;
import marsrover.control.RoverLocationData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static marsrover.control.RoverCompassDirection.EAST;
import static marsrover.control.RoverCompassDirection.NORTH;
import static marsrover.rover.RoverDirectionalCommand.FORWARD;
import static marsrover.rover.RoverDirectionalCommand.TURN_RIGHT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class RoverTest {

    @Test
    public void roverCanMoveToANewPosition() {
        RoverLocationDataService roverLocationDataService = mock(RoverLocationDataService.class);
        RoverMovementService roverMovementService = mock(RoverMovementService.class);

        RoverLocationData originalLocation = new RoverLocationData(new RoverCoordinates(0, 0), NORTH);
        RoverLocationData nextLocation = new RoverLocationData(new RoverCoordinates(3, 3), EAST);
        RoverCommands roverCommands = new RoverCommands(
                List.of(FORWARD, FORWARD, FORWARD, TURN_RIGHT, FORWARD, FORWARD, FORWARD));

        when(roverLocationDataService.getCurrentLocation()).thenReturn(originalLocation);
        when(roverMovementService.move(originalLocation, roverCommands)).thenReturn(nextLocation);

        Rover underTest = new Rover(roverLocationDataService, roverMovementService);

        RoverLocationUpdate result = underTest.completeInstructions(roverCommands);


        RoverLocationUpdate expectedRoverLocationDetails = new RoverLocationUpdate(nextLocation);
        verify(roverLocationDataService).updatePosition(expectedRoverLocationDetails);
        assertThat(result, is(expectedRoverLocationDetails));
    }

}