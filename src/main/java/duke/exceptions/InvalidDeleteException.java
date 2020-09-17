package duke.exceptions;

public class InvalidDeleteException extends DukeException {
    private static String errorMsg = "I ain't sure which task to delete if you ain't sayin'!\n"
            + "Try somethin' like this: \ndelete 1";

    public InvalidDeleteException() {
        super(errorMsg);
    }
}
