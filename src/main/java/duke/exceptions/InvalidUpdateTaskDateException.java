package duke.exceptions;

public class InvalidUpdateTaskDateException extends DukeException {
    private static String errorMsg = "Gotta check yer format for date and times!\n"
            + "Try somethin' like this:\n"
            + "update 1 /date 2020-02-20 1200";

    public InvalidUpdateTaskDateException() {
        super(errorMsg);
    }
}
