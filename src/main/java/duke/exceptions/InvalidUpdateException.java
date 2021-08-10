package duke.exceptions;

public class InvalidUpdateException extends DukeException {
    private static String errorMsg = "Y'all need to learn to use the proper format!\n"
            + "Try somethin' like this:\n"
            + "update 1 /name help spongebob\n"
            + "Or this: \nupdate 1 /date 2020-02-20 1200";

    public InvalidUpdateException() {
        super(errorMsg);
    }
}
