public class ChatbotException extends Exception {

    String message;

    public ChatbotException(String message) {
        super(message);
        this.message = message;
    }
}
