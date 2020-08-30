package seedu.duke;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(("ERROR!! " + message).replaceAll("(?m)^", "\t"));
    }
}