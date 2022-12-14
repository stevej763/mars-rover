package marsrover.rover;

import marsrover.common.MessageHandler;
import marsrover.common.RoverCommand;
import marsrover.common.RoverInstructionsMessage;

import java.util.List;
import java.util.function.Function;

public class RoverMessageHandler implements MessageHandler {

    private final Rover rover;

    public RoverMessageHandler(Rover rover) {
        this.rover = rover;
    }

    public void process(RoverInstructionsMessage roverInstructionsMessage) {
        RoverCommands roverCommands = toRoverCommands(roverInstructionsMessage.getCommands());
        rover.completeInstructions(roverCommands);
    }

    private RoverCommands toRoverCommands(List<RoverCommand> roverMessageDtoCommands) {
        List<RoverDirectionalCommand> roverDirectionalCommands = roverMessageDtoCommands.stream()
                .map(toRoverDirectionalCommand())
                .toList();
        return new RoverCommands(roverDirectionalCommands);
    }

    private Function<RoverCommand, RoverDirectionalCommand> toRoverDirectionalCommand() {
        return roverCommand -> RoverDirectionalCommand.toCommand(roverCommand.name());
    }
}
