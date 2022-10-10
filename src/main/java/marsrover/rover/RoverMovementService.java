package marsrover.rover;

import marsrover.control.RoverCompassDirection;
import marsrover.control.RoverCoordinates;
import marsrover.control.RoverLocationData;

import java.util.List;

import static marsrover.control.RoverCompassDirection.*;
import static marsrover.rover.RoverDirectionalCommand.FORWARD;

public class RoverMovementService {

    public RoverLocationData move(RoverLocationData currentLocation, RoverCommands roverCommands) {
        List<RoverDirectionalCommand> directionalCommands = roverCommands.getRoverDirectionalCommands();

        RoverCompassDirection startingDirection = currentLocation.getRoverCompassDirection();
        RoverCoordinates startingCoordinates = currentLocation.getRoverCoordinates();
        RoverActiveLocation roverActiveLocation = calculateRoverLocation(directionalCommands, startingDirection, startingCoordinates);

        RoverCoordinates updatedCoordinates = new RoverCoordinates(roverActiveLocation.getX(), roverActiveLocation.getY());

        return new RoverLocationData(updatedCoordinates, startingDirection);
    }

    private RoverActiveLocation calculateRoverLocation(List<RoverDirectionalCommand> roverDirectionalCommands, RoverCompassDirection startingDirection, RoverCoordinates startingCoordinates) {
        RoverActiveLocation roverActiveLocation = new RoverActiveLocation(startingCoordinates.getX(), startingCoordinates.getY());
        roverDirectionalCommands.forEach(command -> moveRover(startingDirection, startingCoordinates, roverActiveLocation, command));
        return roverActiveLocation;
    }

    private void moveRover(RoverCompassDirection startingDirection, RoverCoordinates startingCoordinates, RoverActiveLocation roverActiveLocation, RoverDirectionalCommand command) {
        switch (startingDirection) {
            case NORTH -> moveWhenFacingNorth(startingCoordinates, roverActiveLocation, command);
            case SOUTH -> moveWhenFacingSouth(startingCoordinates, roverActiveLocation, command);
            case EAST -> moveWhenFacingEast(startingCoordinates, roverActiveLocation, command);
            case WEST -> moveWhenFacingWest(startingCoordinates, roverActiveLocation, command);
        }
    }

    private void moveWhenFacingWest(RoverCoordinates startingCoordinates, RoverActiveLocation roverActiveLocation, RoverDirectionalCommand command) {
        if (command.equals(FORWARD)) {
            roverActiveLocation.decrementXCoordinate(startingCoordinates.getX());
        } else {
            roverActiveLocation.incrementXCoordinate(startingCoordinates.getX());
        }
    }

    private void moveWhenFacingNorth(RoverCoordinates startingCoordinates, RoverActiveLocation roverActiveLocation, RoverDirectionalCommand command) {
        if (command.equals(FORWARD)) {
            roverActiveLocation.incrementYCoordinate(startingCoordinates.getY());
        } else {
            roverActiveLocation.decrementYCoordinate(startingCoordinates.getY());
        }
    }

    private void moveWhenFacingEast(RoverCoordinates startingCoordinates, RoverActiveLocation roverActiveLocation, RoverDirectionalCommand command) {
        if (command.equals(FORWARD)) {
            roverActiveLocation.incrementXCoordinate(startingCoordinates.getX());
        } else {
            roverActiveLocation.decrementXCoordinate(startingCoordinates.getX());
        }
    }

    private void moveWhenFacingSouth(RoverCoordinates startingCoordinates, RoverActiveLocation roverActiveLocation, RoverDirectionalCommand command) {
        if (command.equals(FORWARD)) {
            roverActiveLocation.decrementYCoordinate(startingCoordinates.getY());
        } else {
            roverActiveLocation.incrementYCoordinate(startingCoordinates.getY());
        }
    }

}
