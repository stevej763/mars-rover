package marsrover.rover;

public class RoverActiveLocation {

    private Integer y;
    private Integer x;

    public RoverActiveLocation(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void updateYCoordinate(Integer y) {
        this.y += y;
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

    public void incrementXCoordinate(Integer currentXCoordinate) {
        updateXCoordinate(currentXCoordinate + 1);

    }

    public void incrementYCoordinate(Integer currentYCoordinate) {
        updateYCoordinate(currentYCoordinate + 1);
    }

    public void decrementXCoordinate(Integer currentXCoordinate) {
        updateXCoordinate(currentXCoordinate - 1);
    }

    public void decrementYCoordinate(Integer startingYCoordinates) {
        updateYCoordinate(startingYCoordinates - 1);
    }
}
