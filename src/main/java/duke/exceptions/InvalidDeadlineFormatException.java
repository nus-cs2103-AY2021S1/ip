package duke.exceptions;

public class InvalidDeadlineFormatException extends DukeException {
    private static String errorMsg = "Y'all need to learn to use the proper format!\n"
            + "Try somethin' like this: \ndeadline build spaceship /by 2019-10-15 2359";

    public InvalidDeadlineFormatException() {
        super(errorMsg);
    }
}
