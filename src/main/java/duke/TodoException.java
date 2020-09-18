package duke;

public class TodoException {
    private final String errorMessage = "OOPS!!! The description of a todo cannot be empty.";
    TodoException() {}

    String getErrorMessage() {
        return this.errorMessage;
    }
}
