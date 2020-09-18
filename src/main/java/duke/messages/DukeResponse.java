package duke.messages;

/**
 * Represents a response to user input in the Duke chatbot.
 */
public class DukeResponse {
    /** The message to be displayed to the user. */
    private final String message;
    /** Whether the chatbot should exit. */
    private final boolean shouldExit;

    /**
     * Constructs a {@code DukeResponse} with the specified message and whether the chatbot should exit.
     *
     * @param message the message to be displayed to the user
     * @param shouldExit whether the chatbot should exit
     */
    public DukeResponse(String message, boolean shouldExit) {
        this.message = message;
        this.shouldExit = shouldExit;
    }

    /**
     * Constructs a {@code DukeResponse} with the specified message while continuing the execution of the chatbot.
     *
     * @param message the message to be displayed to the user
     */
    public DukeResponse(String message) {
        this(message, false);
    }

    /**
     * Returns whether the chatbot should exit.
     *
     * @return true if the chatbot should exit; otherwise false
     */
    public boolean shouldExit() {
        return shouldExit;
    }

    /**
     * Returns a string representation of this {@code DukeResponse}.
     *
     * @return a string representation of this {@code DukeResponse}
     */
    @Override
    public String toString() {
        return message;
    }
}
