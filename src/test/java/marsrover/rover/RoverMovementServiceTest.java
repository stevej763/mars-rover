package marsrover.rover;

import marsrover.control.RoverCoordinates;
import marsrover.control.RoverLocationData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static marsrover.control.RoverCompassDirection.*;
import static marsrover.rover.RoverDirectionalCommand.BACKWARD;
import static marsrover.rover.RoverDirectionalCommand.FORWARD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoverMovementServiceTest {

    private final RoverMovementService underTest = new RoverMovementService();
    private final RoverLocationData initialLocationNorth = new RoverLocationData(new RoverCoordinates(0, 0), NORTH);
    private final RoverLocationData initialLocationSouth = new RoverLocationData(new RoverCoordinates(0, 0), SOUTH);
    private final RoverLocationData initialLocationEast = new RoverLocationData(new RoverCoordinates(0, 0), EAST);
    private final RoverLocationData initialLocationWest = new RoverLocationData(new RoverCoordinates(0, 0), WEST);

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
}