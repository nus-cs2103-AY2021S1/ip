package duke.exception;

/**
 * Separate parse exception just for parse errors.
 */
public class DukeParseException extends DukeException {
    private String extraMessage;
    public DukeParseException(String message) {
        super(message);
    }
    public void setExtraMessage(String extraMessage) {
        this.extraMessage = extraMessage;
    }
    @Override
    public String toString() {
        String message = super.toString();
        if (extraMessage != null) {
            message += "\n\n" + extraMessage;
        }
        return message;
    }
}
