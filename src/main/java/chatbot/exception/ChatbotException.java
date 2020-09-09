package chatbot.exception;

/**
 * This exception is thrown by the chatbot to indicate that an invalid command is given,
 * or an invalid action is encountered.
 */

public class ChatbotException extends Exception {

    protected final String message;

    /**
     * Constructs an exception specific to the chatbot.
     * @param message
     */
    public ChatbotException(String message) {
        super(message);
        this.message = message;
    }
}
