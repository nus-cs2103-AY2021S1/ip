package duke.exceptions;

public class InvalidCommandException extends DukeException {
    private static String errorMsg = "Boy, I don't get what y'all are sayin'!\n"
            + "Try typin' \"help\" to see what commands you can use!";

    public InvalidCommandException() {
        super(errorMsg);
    }
}
