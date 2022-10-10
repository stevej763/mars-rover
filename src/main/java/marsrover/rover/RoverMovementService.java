package marsrover.rover;

import marsrover.control.RoverCompassDirection;
import marsrover.control.RoverCoordinates;
import marsrover.control.RoverLocationData;

import java.util.List;

import static marsrover.control.RoverCompassDirection.*;

public class RoverMovementService {

    public RoverLocationData move(RoverLocationData currentLocation, RoverCommands roverCommands) {
        List<RoverDirectionalCommand> directionalCommands = roverCommands.getRoverDirectionalCommands();

        RoverActiveLocation roverActiveLocation = moveRover(directionalCommands, currentLocation);

        RoverCoordinates updatedCoordinates = new RoverCoordinates(roverActiveLocation.getX(), roverActiveLocation.getY());
        return new RoverLocationData(updatedCoordinates, roverActiveLocation.getCurrentCompassDirection());
    }

    private RoverActiveLocation moveRover(List<RoverDirectionalCommand> directionalCommands, RoverLocationData startingLocation) {
        RoverActiveLocation roverActiveLocation = new RoverActiveLocation(
                startingLocation.getRoverXCoordinate(),
                startingLocation.getRoverYCoordinate(),
                startingLocation.getRoverCompassDirection());
        directionalCommands.forEach(directionToMove -> executeMovement(roverActiveLocation, directionToMove));
        return roverActiveLocation;
    }

    private void executeMovement(RoverActiveLocation roverActiveLocation, RoverDirectionalCommand directionToMove) {
        switch (roverActiveLocation.getCurrentCompassDirection()) {
            case NORTH -> moveWhenFacingNorth(roverActiveLocation, directionToMove);
            case SOUTH -> moveWhenFacingSouth(roverActiveLocation, directionToMove);
            case EAST -> moveWhenFacingEast(roverActiveLocation, directionToMove);
            case WEST -> moveWhenFacingWest(roverActiveLocation, directionToMove);
        }
    }

    private void moveWhenFacingNorth(RoverActiveLocation roverActiveLocation, RoverDirectionalCommand directionToMove) {
        switch (directionToMove) {
            case FORWARD -> roverActiveLocation.incrementYCoordinate();
            case BACKWARD -> roverActiveLocation.decrementYCoordinate();
            case TURN_RIGHT -> turnRoverRight(roverActiveLocation);
            case TURN_LEFT -> turnRoverLeft(roverActiveLocation);
        }
    }

    private void moveWhenFacingWest(RoverActiveLocation roverActiveLocation, RoverDirectionalCommand directionToMove) {
        switch (directionToMove) {
            case FORWARD -> roverActiveLocation.decrementXCoordinate();
            case BACKWARD -> roverActiveLocation.incrementXCoordinate();
            case TURN_RIGHT -> turnRoverRight(roverActiveLocation);
            case TURN_LEFT -> turnRoverLeft(roverActiveLocation);
        }
    }

    private void moveWhenFacingEast(RoverActiveLocation roverActiveLocation, RoverDirectionalCommand directionToMove) {
        switch (directionToMove) {
            case FORWARD -> roverActiveLocation.incrementXCoordinate();
            case BACKWARD -> roverActiveLocation.decrementXCoordinate();
            case TURN_RIGHT -> turnRoverRight(roverActiveLocation);
            case TURN_LEFT -> turnRoverLeft(roverActiveLocation);
        }
    }

    private void moveWhenFacingSouth(RoverActiveLocation roverActiveLocation, RoverDirectionalCommand directionToMove) {
        switch (directionToMove) {
            case FORWARD -> roverActiveLocation.decrementYCoordinate();
            case BACKWARD -> roverActiveLocation.incrementYCoordinate();
            case TURN_RIGHT -> turnRoverRight(roverActiveLocation);
            case TURN_LEFT -> turnRoverLeft(roverActiveLocation);
        }
    }

    private void turnRoverRight(RoverActiveLocation roverActiveLocation) {
        RoverCompassDirection currentDirection = roverActiveLocation.getCurrentCompassDirection();
        switch (currentDirection) {
            case NORTH -> roverActiveLocation.turnRover(EAST);
            case SOUTH -> roverActiveLocation.turnRover(WEST);
            case EAST -> roverActiveLocation.turnRover(SOUTH);
            case WEST -> roverActiveLocation.turnRover(NORTH);
        }
    }

    private void turnRoverLeft(RoverActiveLocation roverActiveLocation) {
        RoverCompassDirection currentDirection = roverActiveLocation.getCurrentCompassDirection();
        switch (currentDirection) {
            case NORTH -> roverActiveLocation.turnRover(WEST);
            case SOUTH -> roverActiveLocation.turnRover(EAST);
            case EAST -> roverActiveLocation.turnRover(NORTH);
            case WEST -> roverActiveLocation.turnRover(SOUTH);
        }
    }
}
