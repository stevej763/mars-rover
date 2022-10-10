package marsrover.rover;

import marsrover.common.RoverCommand;
import marsrover.common.RoverInstructionsMessage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static marsrover.rover.RoverDirectionalCommand.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RoverMessageHandlerTest {

    private final Rover rover = mock(Rover.class);

    @Test
    public void canReceiveForwardCommand() {
        RoverInstructionsMessage roverInstructionsMessage = new RoverInstructionsMessage(List.of(RoverCommand.FORWARD));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverInstructionsMessage);

        verify(rover).completeInstructions(new RoverCommands(List.of(FORWARD)));
    }

    @Test
    public void canReceiveMultipleForwardCommands() {
        RoverInstructionsMessage roverInstructionsMessage = new RoverInstructionsMessage(List.of(
                RoverCommand.FORWARD,
                RoverCommand.FORWARD,
                RoverCommand.FORWARD));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverInstructionsMessage);

        RoverCommands expected = new RoverCommands(List.of(
                FORWARD,
                FORWARD,
                FORWARD));
        verify(rover).completeInstructions(expected);
    }

    @Test
    public void canReceiveReverseCommand() {
        RoverInstructionsMessage roverInstructionsMessage = new RoverInstructionsMessage(List.of(RoverCommand.BACKWARD));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverInstructionsMessage);

        verify(rover).completeInstructions(new RoverCommands(List.of(BACKWARD)));
    }

    @Test
    public void canReceiveMultipleReverseCommands() {
        RoverInstructionsMessage roverInstructionsMessage = new RoverInstructionsMessage(List.of(
                RoverCommand.BACKWARD,
                RoverCommand.BACKWARD,
                RoverCommand.BACKWARD));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverInstructionsMessage);

        RoverCommands expected = new RoverCommands(List.of(
                BACKWARD,
                BACKWARD,
                BACKWARD));
        verify(rover).completeInstructions(expected);
    }

    @Test
    public void canReceiveTurnLeftCommand() {
        RoverInstructionsMessage roverInstructionsMessage = new RoverInstructionsMessage(List.of(RoverCommand.TURN_LEFT));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverInstructionsMessage);

        verify(rover).completeInstructions(new RoverCommands(List.of(TURN_LEFT)));
    }

    @Test
    public void canReceiveTurnRightCommand() {
        RoverInstructionsMessage roverInstructionsMessage = new RoverInstructionsMessage(List.of(RoverCommand.TURN_RIGHT));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverInstructionsMessage);

        RoverCommands expected = new RoverCommands(List.of(TURN_RIGHT));
        verify(rover).completeInstructions(expected);
    }

    @Test
    public void canReceiveMixOfForwardAndReverseCommands() {
        RoverInstructionsMessage roverInstructionsMessage = new RoverInstructionsMessage(List.of(
                RoverCommand.FORWARD,
                RoverCommand.BACKWARD,
                RoverCommand.FORWARD,
                RoverCommand.BACKWARD));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverInstructionsMessage);

        RoverCommands expected = new RoverCommands(List.of(
                FORWARD,
                BACKWARD,
                FORWARD,
                BACKWARD));
        verify(rover).completeInstructions(expected);
    }
}