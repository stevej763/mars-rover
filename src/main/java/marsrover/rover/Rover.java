package marsrover.rover;


import marsrover.control.RoverLocationData;

public class Rover {

    private final RoverLocationDataService roverLocationDataService;
    private RoverMovementService roverMovementService;

    public Rover(RoverLocationDataService roverLocationDataService, RoverMovementService roverMovementService) {
        this.roverLocationDataService = roverLocationDataService;
        this.roverMovementService = roverMovementService;
    }

    public RoverLocationUpdate completeInstructions(RoverCommands roverCommands) {
        RoverLocationData currentLocation = roverLocationDataService.getCurrentLocation();

        RoverLocationData updatedLocation = roverMovementService.move(currentLocation, roverCommands);

        roverLocationDataService.updatePosition(updatedLocation);

        return new RoverLocationUpdate(updatedLocation);
    }

}
