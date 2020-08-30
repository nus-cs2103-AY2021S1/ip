package duke.exception;

public class DukeException extends Exception {
    private String UiMessage;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.UiMessage = errorMessage;
    }

    public String getUiMessage() {
        return this.UiMessage;
    }
}
