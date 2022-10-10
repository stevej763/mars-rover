package marsrover.rover;

import marsrover.control.RoverLocationData;

public class RoverLocationDataService {

    private final RoverLocationMessageSender roverLocationMessageSender;
    private RoverLocationData currentLocation;

    public RoverLocationDataService(RoverLocationData startingLocationData, RoverLocationMessageSender roverLocationMessageSender) {
        this.currentLocation = startingLocationData;
        this.roverLocationMessageSender = roverLocationMessageSender;
    }

    public RoverLocationData getCurrentLocation() {
        return currentLocation;
    }

    public void updatePosition(RoverLocationUpdate nextLocation) {
        this.currentLocation = nextLocation.getRoverLocationData();
        RoverLocationMessageDto roverMessageDto = new RoverLocationMessageDto(getCurrentLocation().getRoverCoordinates(), getCurrentLocation().getRoverCompassDirection());
        roverLocationMessageSender.send(roverMessageDto);
    }
}
