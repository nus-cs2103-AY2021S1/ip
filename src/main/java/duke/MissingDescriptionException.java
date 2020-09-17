package duke;

public class MissingDescriptionException extends Exception {

    public MissingDescriptionException(String s) {
        super("OOPS!!! The description of a " + s + " cannot be empty.");
    }
}
