package marsrover.control;

import marsrover.common.RoverCommand;
import marsrover.common.RoverMessageDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static marsrover.common.RoverCommand.BACKWARD;
import static marsrover.common.RoverCommand.FORWARD;
import static marsrover.control.RoverCompassDirection.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

public class RoverControllerTest {

    private static final RoverCoordinates DEFAULT_ROVER_COORDINATES = new RoverCoordinates(0, 0);
    private static final RoverLocationData DEFAULT_ROVER_LOCATION_DATA = new RoverLocationData(DEFAULT_ROVER_COORDINATES, NORTH);

    private final Sender roverMessageSender = mock(RoverMessageSender.class);

    @ParameterizedTest
    @MethodSource("roverCoordinates")
    public void canReturnCurrentRoverPositionAtZeroZero(RoverLocationData roverLocationData, RoverCoordinates expected) {
        RoverController underTest = new RoverController(roverLocationData, roverMessageSender);

        assertThat(underTest.getRoverCoordinates(), is(expected));
    }

    @Test
    public void canReturnCurrentDirectionOfRoverWhenFacingNorth() {
        RoverController underTest = new RoverController(DEFAULT_ROVER_LOCATION_DATA, roverMessageSender);

        assertThat(underTest.getRoverCompassDirection(), is(NORTH));
    }

    @Test
    public void canReturnCurrentDirectionOfRoverWhenFacingSouth() {
        RoverLocationData roverLocationData = new RoverLocationData(DEFAULT_ROVER_COORDINATES, SOUTH);

        RoverController underTest = new RoverController(roverLocationData, roverMessageSender);

        assertThat(underTest.getRoverCompassDirection(), is(SOUTH));
    }

    @Test
    public void canReturnCurrentDirectionOfRoverWhenFacingEAST() {
        RoverLocationData roverLocationData = new RoverLocationData(DEFAULT_ROVER_COORDINATES, EAST);

        RoverController underTest = new RoverController(roverLocationData, roverMessageSender);

        assertThat(underTest.getRoverCompassDirection(), is(EAST));
    }

    @Test
    public void canReturnCurrentDirectionOfRoverWhenFacingWEST() {
        RoverLocationData roverLocationData = new RoverLocationData(DEFAULT_ROVER_COORDINATES, WEST);

        RoverController underTest = new RoverController(roverLocationData, roverMessageSender);

        assertThat(underTest.getRoverCompassDirection(), is(WEST));
    }

    @Test
    public void instructionsCanBeSentToMoveTheRoverForwardOneGridPosition() {
        Character[] commandArray = new Character[]{'f'};

        RoverController underTest = new RoverController(DEFAULT_ROVER_LOCATION_DATA, roverMessageSender);

        Boolean result = underTest.sendCommand(commandArray);

        verify(roverMessageSender).send(new RoverMessageDto(List.of(FORWARD)));
        assertThat(result, is(TRUE));
    }

    @Test
    public void instructionsCanBeSentToMoveTheRoverForwardMultipleGridPositions() {
        Character[] commandArray = new Character[]{'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f'};

        RoverController underTest = new RoverController(DEFAULT_ROVER_LOCATION_DATA, roverMessageSender);

        Boolean result = underTest.sendCommand(commandArray);
        List<RoverCommand> expectedRoverCommands = List.of(
                FORWARD, FORWARD, FORWARD, FORWARD, FORWARD,
                FORWARD, FORWARD, FORWARD, FORWARD, FORWARD);

        verify(roverMessageSender).send(new RoverMessageDto(expectedRoverCommands));
        assertThat(result, is(TRUE));
    }

    @Test
    public void instructionsCanBeSentToReverseTheRoverOneGridPosition() {
        Character[] commandArray = new Character[]{'b'};

        RoverController underTest = new RoverController(DEFAULT_ROVER_LOCATION_DATA, roverMessageSender);

        Boolean result = underTest.sendCommand(commandArray);
        verify(roverMessageSender).send(new RoverMessageDto(List.of(BACKWARD)));

        assertThat(result, is(TRUE));
    }

    @Test
    public void instructionsCanBeSentToReverseTheRoverMultipleGridPositions() {
        Character[] commandArray = new Character[]{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};

        RoverController underTest = new RoverController(DEFAULT_ROVER_LOCATION_DATA, roverMessageSender);

        Boolean result = underTest.sendCommand(commandArray);
        List<RoverCommand> expectedRoverCommands = List.of(
                BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD,
                BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD);
        verify(roverMessageSender).send(new RoverMessageDto(expectedRoverCommands));

        assertThat(result, is(TRUE));
    }

    @Test
    public void instructionsCanBeSentToTurnTheRoverOneGridPositionLeft() {
        Character[] commandArray = new Character[]{'l'};

        RoverController underTest = new RoverController(DEFAULT_ROVER_LOCATION_DATA, roverMessageSender);

        Boolean result = underTest.sendCommand(commandArray);

        verify(roverMessageSender).send(new RoverMessageDto(List.of(RoverCommand.TURN_LEFT)));
        assertThat(result, is(TRUE));
    }

    @Test
    public void instructionsCanBeSentToTurnTheRoverOneGridPositionRight() {
        Character[] commandArray = new Character[]{'r'};

        RoverController underTest = new RoverController(DEFAULT_ROVER_LOCATION_DATA, roverMessageSender);

        Boolean result = underTest.sendCommand(commandArray);

        verify(roverMessageSender).send(new RoverMessageDto(List.of(RoverCommand.TURN_RIGHT)));
        assertThat(result, is(TRUE));
    }

    @Test
    public void instructionsNotSentWhenAttemptingToSendInvalidCommand() {
        Character[] commandArray = new Character[]{'X'};

        RoverController underTest = new RoverController(DEFAULT_ROVER_LOCATION_DATA, roverMessageSender);

        Boolean result = underTest.sendCommand(commandArray);

        verifyNoInteractions(roverMessageSender);
        assertThat(result, is(FALSE));
    }

    private static Stream<Arguments> roverCoordinates() {
        return Stream.of(
                arguments(new RoverLocationData(new RoverCoordinates(0, 0), NORTH), new RoverCoordinates(0, 0)),
                arguments(new RoverLocationData(new RoverCoordinates(1, 1), NORTH), new RoverCoordinates(1, 1)),
                arguments(new RoverLocationData(new RoverCoordinates(12, 12), NORTH), new RoverCoordinates(12, 12)),
                arguments(new RoverLocationData(new RoverCoordinates(123, 123), NORTH), new RoverCoordinates(123, 123)),
                arguments(new RoverLocationData(new RoverCoordinates(1234, 1234), NORTH), new RoverCoordinates(1234, 1234))
        );
    }
}