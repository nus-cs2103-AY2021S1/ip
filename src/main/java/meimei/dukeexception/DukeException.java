package meimei.dukeexception;

/**
 * Represents exceptions unique to the bot.
 */
public class DukeException extends Exception {

    /**
     * Public constructor.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Aiyo! " + super.getMessage();
    }
}
