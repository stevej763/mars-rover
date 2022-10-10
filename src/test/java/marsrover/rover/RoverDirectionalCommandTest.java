package marsrover.rover;

import org.junit.jupiter.api.Test;

import static marsrover.rover.RoverDirectionalCommand.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverDirectionalCommandTest {

    @Test
    public void canConvertToRoverDirectionalCommands() {
        assertThat(RoverDirectionalCommand.valueOf("FORWARD"), is(FORWARD));
        assertThat(RoverDirectionalCommand.valueOf("BACKWARD"), is(BACKWARD));
        assertThat(RoverDirectionalCommand.valueOf("TURN_LEFT"), is(TURN_LEFT));
        assertThat(RoverDirectionalCommand.valueOf("TURN_RIGHT"), is(TURN_RIGHT));
    }

    @Test
    public void throwsExceptionWhenUnrecognisedCommandGiven() {
        IllegalRoverDirectionalCommandException expected = assertThrows(
                IllegalRoverDirectionalCommandException.class,
                () -> toCommand("X"));
        assertThat(expected, is(new IllegalRoverDirectionalCommandException("X")));
        assertThat(expected.getMessage(), is("Cannot parse instructions due to invalid command. command=X"));
    }
}