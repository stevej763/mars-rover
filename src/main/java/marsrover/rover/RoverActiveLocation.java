package marsrover.rover;

import marsrover.control.RoverCompassDirection;

public class RoverActiveLocation {

    private RoverCompassDirection currentCompassDirection;
    private Integer y;
    private Integer x;

    public RoverActiveLocation(Integer x, Integer y, RoverCompassDirection startingCompassDirection) {
        this.currentCompassDirection = startingCompassDirection;
        this.x = x;
        this.y = y;
    }

    public void updateYCoordinate(Integer y) {
        this.y = y;
    }

    public void updateXCoordinate(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getX() {
        return x;
    }

    public void incrementXCoordinate() {
        updateXCoordinate(x + 1);
    }

    public void incrementYCoordinate() {
        updateYCoordinate(y + 1);
    }

    public void decrementXCoordinate() {
        updateXCoordinate(x - 1);
    }

    public void decrementYCoordinate() {
        updateYCoordinate(y - 1);
    }

    public RoverCompassDirection getCurrentCompassDirection() {
        return currentCompassDirection;
    }

    public void turnRover(RoverCompassDirection roverCompassDirection) {
        this.currentCompassDirection = roverCompassDirection;
    }
}
