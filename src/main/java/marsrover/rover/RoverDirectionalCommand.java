package marsrover.rover;

public enum RoverDirectionalCommand {
    FORWARD, BACKWARD, TURN_LEFT, TURN_RIGHT;

    public static RoverDirectionalCommand toCommand(String command) {
        if (command.equals("FORWARD")) {
            return FORWARD;
        }
        if (command.equals("BACKWARDS")) {
            return BACKWARD;
        }
        if (command.equals("TURN_LEFT")) {
            return TURN_LEFT;
        }
        if (command.equals("TURN_RIGHT")) {
            return TURN_RIGHT;
        }
        throw new IllegalRoverDirectionalCommandException(command);
    }
}
