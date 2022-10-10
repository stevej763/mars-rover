package marsrover.control;

import marsrover.common.RoverMessageDto;

public interface Sender {
    void send(RoverMessageDto roverMessageDto);
}
