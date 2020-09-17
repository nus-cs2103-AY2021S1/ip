package duke.exceptions;

public class InvalidUpdateTodoException extends DukeException {
    private static String errorMsg = "Todos ain't got no date and time!\n"
            + "Try somethin' like this:\n"
            + "update 1 /name help spongebob";

    public InvalidUpdateTodoException() {
        super(errorMsg);
    }
}
