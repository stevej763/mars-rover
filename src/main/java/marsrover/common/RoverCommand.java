package marsrover.common;

public enum RoverCommand {
    FORWARD,
    BACKWARD,
    TURN_LEFT,
    TURN_RIGHT;

    public static RoverCommand toCommand(Character letter) {
        if (letter == 'f') {
            return FORWARD;
        }
        if (letter == 'b') {
            return BACKWARD;
        }
        if (letter == 'l') {
            return TURN_LEFT;
        }
        if (letter == 'r') {
            return TURN_RIGHT;
        }
        throw new IllegalRoverCommandException(letter);
    }
}
