package duke.exception;

public class DukeException extends Exception {
    private String uiMessage;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.uiMessage = errorMessage;
    }

    public String getUiMessage() {
        return this.uiMessage;
    }
}
