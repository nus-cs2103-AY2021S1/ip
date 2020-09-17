package duke.exceptions;

public class InvalidEventFormatException extends DukeException {
    private static String errorMsg = "Y'all need to learn to use the proper format!\n"
            + "Try somethin' like this: \nevent karate competition /at 2019-10-15 1200";

    public InvalidEventFormatException() {
        super(errorMsg);
    }
}
