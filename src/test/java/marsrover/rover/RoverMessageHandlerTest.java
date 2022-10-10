package marsrover.rover;

import marsrover.common.RoverCommand;
import marsrover.common.RoverMessageDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static marsrover.rover.RoverDirectionalCommand.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RoverMessageHandlerTest {

    private final Rover rover = mock(Rover.class);

    @Test
    public void canReceiveForwardCommand() {
        RoverMessageDto roverMessageDto = new RoverMessageDto(List.of(RoverCommand.FORWARD));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverMessageDto);

        verify(rover).completeInstructions(new RoverCommands(List.of(FORWARD)));
    }

    @Test
    public void canReceiveMultipleForwardCommands() {
        RoverMessageDto roverMessageDto = new RoverMessageDto(List.of(
                RoverCommand.FORWARD,
                RoverCommand.FORWARD,
                RoverCommand.FORWARD));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverMessageDto);

        RoverCommands expected = new RoverCommands(List.of(
                FORWARD,
                FORWARD,
                FORWARD));
        verify(rover).completeInstructions(expected);
    }

    @Test
    public void canReceiveReverseCommand() {
        RoverMessageDto roverMessageDto = new RoverMessageDto(List.of(RoverCommand.BACKWARDS));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverMessageDto);

        verify(rover).completeInstructions(new RoverCommands(List.of(BACKWARDS)));
    }

    @Test
    public void canReceiveMultipleReverseCommands() {
        RoverMessageDto roverMessageDto = new RoverMessageDto(List.of(
                RoverCommand.BACKWARDS,
                RoverCommand.BACKWARDS,
                RoverCommand.BACKWARDS));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverMessageDto);

        RoverCommands expected = new RoverCommands(List.of(
                BACKWARDS,
                BACKWARDS,
                BACKWARDS));
        verify(rover).completeInstructions(expected);
    }

    @Test
    public void canReceiveTurnLeftCommand() {
        RoverMessageDto roverMessageDto = new RoverMessageDto(List.of(RoverCommand.TURN_LEFT));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverMessageDto);

        verify(rover).completeInstructions(new RoverCommands(List.of(TURN_LEFT)));
    }

    @Test
    public void canReceiveTurnRightCommand() {
        RoverMessageDto roverMessageDto = new RoverMessageDto(List.of(RoverCommand.TURN_RIGHT));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverMessageDto);

        RoverCommands expected = new RoverCommands(List.of(TURN_RIGHT));
        verify(rover).completeInstructions(expected);
    }

    @Test
    public void canReceiveMixOfForwardAndReverseCommands() {
        RoverMessageDto roverMessageDto = new RoverMessageDto(List.of(
                RoverCommand.FORWARD,
                RoverCommand.BACKWARDS,
                RoverCommand.FORWARD,
                RoverCommand.BACKWARDS));
        RoverMessageHandler underTest = new RoverMessageHandler(rover);

        underTest.process(roverMessageDto);

        RoverCommands expected = new RoverCommands(List.of(
                FORWARD,
                BACKWARDS,
                FORWARD,
                BACKWARDS));
        verify(rover).completeInstructions(expected);
    }
}