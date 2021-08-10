package duke.exceptions;

public class InvalidFindException extends DukeException {
    private static String errorMsg = "I ain't sure what you want to find if you ain't sayin'!\n"
            + "Try somethin' like this: \nfind karate";

    public InvalidFindException() {
        super(errorMsg);
    }
}
