package marsrover.common;

public interface Sender<T> {
    void send(T message);
}
