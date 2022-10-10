package marsrover.rover;

import marsrover.control.RoverCoordinates;
import marsrover.control.RoverLocationData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static marsrover.control.RoverCompassDirection.*;
import static marsrover.rover.RoverDirectionalCommand.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RoverMovementServiceTest {

    private final RoverMovementService underTest = new RoverMovementService();
    private final RoverLocationData initialLocationNorth = new RoverLocationData(new RoverCoordinates(0, 0), NORTH);
    private final RoverLocationData initialLocationSouth = new RoverLocationData(new RoverCoordinates(0, 0), SOUTH);
    private final RoverLocationData initialLocationEast = new RoverLocationData(new RoverCoordinates(0, 0), EAST);
    private final RoverLocationData initialLocationWest = new RoverLocationData(new RoverCoordinates(0, 0), WEST);

    @Test
    public void canHandleStartingFromNonZeroPosition() {
        RoverLocationData result = underTest.move(new RoverLocationData(new RoverCoordinates(100, 100), NORTH), new RoverCommands(List.of(FORWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(100, 101), NORTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleAForwardMovementWhenFacingNorth() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(FORWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 1), NORTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMultipleForwardMovementsWhenFacingNorth() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(FORWARD, FORWARD, FORWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 3), NORTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleABackwardMovementWhenFacingNorth() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(BACKWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, -1), NORTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMultipleBackwardMovementsWhenFacingNorth() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(BACKWARD, BACKWARD, BACKWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, -3), NORTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMovingForwardsAndBackwardsWhenFacingNorth() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(BACKWARD, FORWARD, BACKWARD, FORWARD, FORWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 1), NORTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMovingForwardWhenFacingSouth() {
        RoverLocationData result = underTest.move(initialLocationSouth, new RoverCommands(List.of(FORWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, -1), SOUTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMovingBackwardWhenFacingSouth() {
        RoverLocationData result = underTest.move(initialLocationSouth, new RoverCommands(List.of(BACKWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 1), SOUTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMovingForwardsAndBackwardsWhenFacingSouth() {
        RoverLocationData result = underTest.move(initialLocationSouth, new RoverCommands(List.of(BACKWARD, FORWARD, BACKWARD, FORWARD, FORWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, -1), SOUTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMovingForwardWhenFacingEast() {
        RoverLocationData result = underTest.move(initialLocationEast, new RoverCommands(List.of(FORWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(1, 0), EAST);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMovingBackwardWhenFacingEast() {
        RoverLocationData result = underTest.move(initialLocationEast, new RoverCommands(List.of(BACKWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(-1, 0), EAST);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMovingForwardsAndBackwardsWhenFacingEast() {
        RoverLocationData result = underTest.move(initialLocationEast, new RoverCommands(List.of(BACKWARD, FORWARD, BACKWARD, FORWARD, FORWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(1, 0), EAST);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMovingForwardWhenFacingWest() {
        RoverLocationData result = underTest.move(initialLocationWest, new RoverCommands(List.of(FORWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(-1, 0), WEST);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMovingBackwardWhenFacingWest() {
        RoverLocationData result = underTest.move(initialLocationWest, new RoverCommands(List.of(BACKWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(1, 0), WEST);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleMovingForwardsAndBackwardsWhenFacingWest() {
        RoverLocationData result = underTest.move(initialLocationWest, new RoverCommands(List.of(BACKWARD, FORWARD, BACKWARD, FORWARD, FORWARD)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(-1, 0), WEST);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleTurningRightFromNorthToEast() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(TURN_RIGHT)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 0), EAST);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleTurningRightFromNorthToSouth() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(TURN_RIGHT, TURN_RIGHT)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 0), SOUTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleTurningRightFromNorthToWest() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(TURN_RIGHT, TURN_RIGHT, TURN_RIGHT)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 0), WEST);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleTurningRightFromNorthToBackToNorth() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(TURN_RIGHT, TURN_RIGHT, TURN_RIGHT, TURN_RIGHT)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 0), NORTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleTurningLeftFromNorthToWest() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(TURN_LEFT)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 0), WEST);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleTurningLeftFromNorthToSouth() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(TURN_LEFT, TURN_LEFT)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 0), SOUTH);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleTurningLeftFromNorthToEast() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(TURN_LEFT, TURN_LEFT, TURN_LEFT)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 0), EAST);
        assertThat(result, is(expectedLocationData));
    }

    @Test
    public void canHandleTurningLeftFromNorthToBackToNorth() {
        RoverLocationData result = underTest.move(initialLocationNorth, new RoverCommands(List.of(TURN_LEFT, TURN_LEFT, TURN_LEFT, TURN_LEFT)));

        RoverLocationData expectedLocationData = new RoverLocationData(new RoverCoordinates(0, 0), NORTH);
        assertThat(result, is(expectedLocationData));
    }

    @ParameterizedTest
    @MethodSource(value = "roverPaths")
    public void canHandleMovingAndTurning(RoverCommands testPath, RoverLocationData expectedEndPoint) {
        RoverLocationData result = underTest.move(initialLocationNorth, testPath);

        assertThat(result, is(expectedEndPoint));
    }

    public static Stream<Arguments> roverPaths() {
        return Stream.of(
                arguments(new RoverCommands(List.of(FORWARD, FORWARD, FORWARD, TURN_RIGHT)), new RoverLocationData(new RoverCoordinates(0, 3), EAST)),
                arguments(new RoverCommands(List.of(FORWARD, TURN_LEFT, FORWARD, TURN_RIGHT, FORWARD, TURN_LEFT, FORWARD, TURN_RIGHT)), new RoverLocationData(new RoverCoordinates(-2, 2), NORTH)),
                arguments(new RoverCommands(List.of(FORWARD, FORWARD, FORWARD, TURN_RIGHT, FORWARD, FORWARD, FORWARD, TURN_RIGHT, FORWARD, FORWARD, FORWARD, TURN_RIGHT, FORWARD, FORWARD, FORWARD)),
                        new RoverLocationData(new RoverCoordinates(0, 0), WEST)),
                arguments(new RoverCommands(List.of(BACKWARD, BACKWARD, BACKWARD, TURN_LEFT, BACKWARD, BACKWARD, BACKWARD, TURN_LEFT, BACKWARD, BACKWARD, BACKWARD, TURN_LEFT, BACKWARD, BACKWARD, BACKWARD)),
                        new RoverLocationData(new RoverCoordinates(0, 0), EAST)),
                arguments(new RoverCommands(List.of(
                                FORWARD,
                                TURN_RIGHT,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                TURN_LEFT,
                                TURN_LEFT,
                                TURN_LEFT,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD,
                                FORWARD)),
                        new RoverLocationData(new RoverCoordinates(9, -13), SOUTH))
        );
    }
}