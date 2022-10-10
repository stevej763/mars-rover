package marsrover.rover;

import marsrover.control.RoverCompassDirection;
import marsrover.control.RoverCoordinates;
import marsrover.control.RoverLocationData;

import java.util.List;

import static marsrover.control.RoverCompassDirection.*;
import static marsrover.rover.RoverDirectionalCommand.*;

public class RoverMovementService {

    public RoverLocationData move(RoverLocationData currentLocation, RoverCommands roverCommands) {
        List<RoverDirectionalCommand> directionalCommands = roverCommands.getRoverDirectionalCommands();

        RoverActiveLocation roverActiveLocation = calculateRoverLocation(directionalCommands, currentLocation);

        RoverCoordinates updatedCoordinates = new RoverCoordinates(roverActiveLocation.getX(), roverActiveLocation.getY());

        return new RoverLocationData(updatedCoordinates, roverActiveLocation.getCurrentCompassDirection());
    }

    private RoverActiveLocation calculateRoverLocation(List<RoverDirectionalCommand> directionalCommands, RoverLocationData startingLocation) {
        RoverActiveLocation roverActiveLocation = new RoverActiveLocation(
                startingLocation.getRoverXCoordinate(),
                startingLocation.getRoverYCoordinate(),
                startingLocation.getRoverCompassDirection());
        directionalCommands.forEach(directionToMove -> moveRover(roverActiveLocation, directionToMove));
        return roverActiveLocation;
    }

    private void moveRover(RoverActiveLocation roverActiveLocation, RoverDirectionalCommand directionToMove) {
        switch (roverActiveLocation.getCurrentCompassDirection()) {
            case NORTH -> moveWhenFacingNorth(roverActiveLocation, directionToMove);
            case SOUTH -> moveWhenFacingSouth(roverActiveLocation, directionToMove);
            case EAST -> moveWhenFacingEast(roverActiveLocation, directionToMove);
            case WEST -> moveWhenFacingWest(roverActiveLocation, directionToMove);
        }
    }

    private void moveWhenFacingNorth(RoverActiveLocation roverActiveLocation, RoverDirectionalCommand directionToMove) {
        if (directionToMove.equals(FORWARD)) {
            roverActiveLocation.incrementYCoordinate();
        } else if (directionToMove.equals(BACKWARD)) {
            roverActiveLocation.decrementYCoordinate();
        } else if (directionToMove.equals(TURN_RIGHT)) {
            turnRoverRight(roverActiveLocation);
        } else if (directionToMove.equals(TURN_LEFT)) {
            turnRoverLeft(roverActiveLocation);
        }
    }


    private void moveWhenFacingWest(RoverActiveLocation roverActiveLocation, RoverDirectionalCommand directionToMove) {
        if (directionToMove.equals(FORWARD)) {
            roverActiveLocation.decrementXCoordinate();
        } else if (directionToMove.equals(BACKWARD)) {
            roverActiveLocation.incrementXCoordinate();
        } else if (directionToMove.equals(TURN_RIGHT)) {
            turnRoverRight(roverActiveLocation);
        } else if (directionToMove.equals(TURN_LEFT)) {
            turnRoverLeft(roverActiveLocation);
        }
    }

    private void moveWhenFacingEast(RoverActiveLocation roverActiveLocation, RoverDirectionalCommand directionToMove) {
        if (directionToMove.equals(FORWARD)) {
            roverActiveLocation.incrementXCoordinate();
        } else if (directionToMove.equals(BACKWARD)) {
            roverActiveLocation.decrementXCoordinate();
        } else if (directionToMove.equals(TURN_RIGHT)) {
            turnRoverRight(roverActiveLocation);
        } else if (directionToMove.equals(TURN_LEFT)) {
            turnRoverLeft(roverActiveLocation);
        }
    }

    private void moveWhenFacingSouth(RoverActiveLocation roverActiveLocation, RoverDirectionalCommand directionToMove) {
        if (directionToMove.equals(FORWARD)) {
            roverActiveLocation.decrementYCoordinate();
        } else if (directionToMove.equals(BACKWARD)) {
            roverActiveLocation.incrementYCoordinate();
        } else if (directionToMove.equals(TURN_RIGHT)) {
            turnRoverRight(roverActiveLocation);
        } else if (directionToMove.equals(TURN_LEFT)) {
            turnRoverLeft(roverActiveLocation);
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
