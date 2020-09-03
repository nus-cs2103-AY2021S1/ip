package duke;

public class TodoException {
    private String errorMessage = "OOPS!!! The description of a todo cannot be empty.";
    TodoException() {}

    String getErrorMessage() {
        return this.errorMessage;
    }
}
