package marsrover.control;

import marsrover.common.IllegalRoverCommandException;
import marsrover.common.RoverCommand;
import marsrover.common.RoverMessageDto;

import java.util.List;
import java.util.stream.Stream;

public class RoverController {

    private static final Boolean SUCCESS = Boolean.TRUE;
    private static final Boolean FAILURE = Boolean.FALSE;

    private final RoverLocationData roverLocationData;
    private final Sender roverMessageSender;

    public RoverController(RoverLocationData roverLocationData, Sender roverMessageSender) {
        this.roverLocationData = roverLocationData;
        this.roverMessageSender = roverMessageSender;
    }

    public RoverCoordinates getRoverCoordinates() {
        return roverLocationData.getRoverCoordinates();
    }

    public RoverCompassDirection getRoverCompassDirection() {
        return roverLocationData.getRoverCompassDirection();
    }

    public Boolean sendCommand(Character[] commandArray) {
        try {
            sendCommandToRover(commandArray);
            return SUCCESS;
        } catch (IllegalRoverCommandException exception) {
            return FAILURE;
        }
    }

    private void sendCommandToRover(Character[] commandArray) {
        RoverMessageDto roverMessageDto = convertToRoverMessageDto(commandArray);
        roverMessageSender.send(roverMessageDto);
    }

    private RoverMessageDto convertToRoverMessageDto(Character[] commandArray) throws IllegalRoverCommandException {
        List<RoverCommand> roverCommands = Stream.of(commandArray)
                .map(RoverCommand::toCommand)
                .toList();
        return new RoverMessageDto(roverCommands);
    }
}
