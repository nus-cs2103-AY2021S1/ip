package duke.exceptions;

public class InvalidTodoFormatException extends DukeException {
    private static String errorMsg = "Y'all need to learn to use the proper format!\n"
            + "Try somethin' like this: \ntodo scold spongebob";

    public InvalidTodoFormatException() {
        super(errorMsg);
    }
}
