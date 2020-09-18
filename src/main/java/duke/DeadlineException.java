package duke;

public class DeadlineException {
    private String errorMessage = "OOPS!!! The description of a deadline cannot be empty.";
    DeadlineException() {}

    String getErrorMessage() {
        return this.errorMessage;
    }
}
