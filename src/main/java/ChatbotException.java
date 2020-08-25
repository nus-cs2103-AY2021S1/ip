/**
 * This exception is thrown by the chatbot to indicate that an invalid command is given,
 * or an invalid action is encountered.
 */

public class ChatbotException extends Exception {

    String message;

    public ChatbotException(String message) {
        super(message);
        this.message = message;
    }
}
