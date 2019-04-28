package tsp;

public class RoomDoesNotExistException extends Exception{
    public RoomDoesNotExistException(String room) {
        super("This is room is not in the school you are searching" + room);
    }
}
