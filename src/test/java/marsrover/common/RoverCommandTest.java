package marsrover.common;

import org.junit.jupiter.api.Test;

import static marsrover.common.RoverCommand.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverCommandTest {

    @Test
    public void throwsExceptionWhenUnrecognisedCommandGiven() {
        IllegalRoverCommandException expected = assertThrows(IllegalRoverCommandException.class, () -> RoverCommand.toCommand('X'));
        assertThat(expected, is(new IllegalRoverCommandException('X')));
        assertThat(expected.getMessage(), is("Cannot parse instructions due to invalid command. command=X"));
    }

    @Test
    public void canParseAForwardCommand() {
        assertThat(RoverCommand.toCommand('f'), is(FORWARD));
    }

    @Test
    public void canParseAReverseCommand() {
        assertThat(RoverCommand.toCommand('b'), is(BACKWARDS));
    }

    @Test
    public void canParseATurnLeftCommand() {
        assertThat(RoverCommand.toCommand('l'), is(TURN_LEFT));
    }

    @Test
    public void canParseATurnRightCommand() {
        assertThat(RoverCommand.toCommand('r'), is(TURN_RIGHT));
    }

}