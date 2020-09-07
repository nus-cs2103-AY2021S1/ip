package duke.exception;

/**
 * Separate parse exception just for parse errors.
 */
public class DukeCustomException extends DukeException {
    private String extraMessage;
    public DukeCustomException(String message) {
        super(message);
    }
    public void setExtraMessage(String extraMessage) {
        if (this.extraMessage == null) {
            this.extraMessage = extraMessage;
        }
    }
    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (extraMessage != null) {
            message += "\n\n" + extraMessage;
        }
        return message;
    }
}
