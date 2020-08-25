package duke.exception;

/**
 * Represents exception to be thrown when users enters any invalid command ot the bot.
 */
public class InvalidCommand extends Exception{
    private String errorMessage;

    /**
     * Creates an InvalidCommand object with appripriate error message to be displayed.
     *
     * @param errorMessage Error message to be displayed to users.
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Displays InvalidCommand as the customised error message.
     *
     * @return Error Message.
     */
    @Override
    public String toString() {
        return this.errorMessage;
    }
}
