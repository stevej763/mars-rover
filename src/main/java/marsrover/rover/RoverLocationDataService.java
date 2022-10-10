package marsrover.rover;

import marsrover.control.RoverLocationData;

public class RoverLocationDataService {

    private RoverLocationData currentLocation;

    public RoverLocationDataService(RoverLocationData startingLocationData) {
        this.currentLocation = startingLocationData;
    }

    public RoverLocationData getCurrentLocation() {
        return currentLocation;
    }

    public void updatePosition(RoverLocationData nextLocation) {
        this.currentLocation = nextLocation;
    }
}
