package duke.exceptions;

public class InvalidDoneException extends DukeException {
    private static String errorMsg = "I ain't sure which task to mark done if you ain't sayin'!\n"
            + "Try somethin' like this: \ndone 1";

    public InvalidDoneException() {
        super(errorMsg);
    }
}
