package duke;

public class EventException {
    private final String errorMessage = "OOPS!!! The description of a event cannot be empty.";

    EventException() {}

    String getErrorMessage() {
        return this.errorMessage;
    }
}
