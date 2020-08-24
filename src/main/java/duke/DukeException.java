package duke;

public class DukeException extends Exception{
    public DukeException(String message) {
        super("Hi my friend, " + message);
    }
}
