/**
 * Represents an exception that can be thrown by the Duke chatbot.
 */
abstract class DukeException extends Exception {
    protected String message;

    DukeException(String message) {
        this.message = "Something went wrong... \n " + message;
    }

    @Override
    public String toString() {
        return message;
    }
}

