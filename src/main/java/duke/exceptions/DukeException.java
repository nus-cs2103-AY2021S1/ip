package duke.exceptions;

public class DukeException extends Exception {

    protected String message;
    protected String friendlyMessage;

    public DukeException(String message) {
        this.message = message;
        this.friendlyMessage = message;
    }

    public DukeException(String message, String friendlyMessage) {
        this.message = message;
        this.friendlyMessage = friendlyMessage;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getFriendlyMessage() {
        return this.friendlyMessage;
    }
}
