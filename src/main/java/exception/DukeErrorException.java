package exception;

public class DukeErrorException extends Exception {

    public DukeErrorException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Invalid use of Duke Chatbot: " + super.getMessage();
    }
}
