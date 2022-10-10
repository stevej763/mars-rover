package marsrover.rover;

import marsrover.common.RoverInstructionsMessage;
import marsrover.control.RoverCoordinates;
import marsrover.control.RoverLocationData;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import java.util.List;

import static marsrover.common.RoverCommand.*;
import static marsrover.control.RoverCompassDirection.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;

public class RoverIntegrationTest {

    @Test
    public void roverCanReceiveCommandsAndMove() {
        RoverCoordinates roverCoordinates = new RoverCoordinates(0, 0);
        RoverLocationData roverLocationData = new RoverLocationData(roverCoordinates, SOUTH);
        RoverLocationMessageSender roverLocationMessageSender = new RoverLocationMessageSender();

        RoverLocationDataService roverLocationDataService = new RoverLocationDataService(roverLocationData, roverLocationMessageSender);
        RoverMovementService roverMovementService = new RoverMovementService();
        Rover rover = new Rover(roverLocationDataService, roverMovementService);

        RoverMessageHandler roverMessageHandler = new RoverMessageHandler(rover);

        RoverInstructionsMessage roverInstructionsMessage = new RoverInstructionsMessage(List.of(
                FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, TURN_RIGHT,
                FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, TURN_LEFT,
                FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, TURN_RIGHT,
                FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, TURN_LEFT,
                FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, TURN_RIGHT,
                FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, TURN_LEFT,
                TURN_LEFT, TURN_LEFT, TURN_LEFT,
                TURN_RIGHT, TURN_RIGHT, TURN_RIGHT, TURN_RIGHT, TURN_RIGHT, TURN_RIGHT));

        roverMessageHandler.process(roverInstructionsMessage);

        assertThat(roverLocationDataService.getCurrentLocation(), is(new RoverLocationData(new RoverCoordinates(-36, -36), EAST)));
    }
}
