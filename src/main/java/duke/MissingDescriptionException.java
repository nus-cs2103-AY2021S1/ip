package duke;

public class MissingDescriptionException extends Exception {
    public MissingDescriptionException(String errMessage) {
        super("\n     ☹ OOPS!!! The description of " + errMessage + " cannot be empty.");
    }
}
