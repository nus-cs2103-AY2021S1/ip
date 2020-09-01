package mugexception;

/**
 * Error that cause by Mug.
 */
public class MugException extends Exception {

    /**
     * Constructs a new Mug Exception with specific detail message
     *
     * @param message error Message
     */
    public MugException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Wrong command given/lack of information";
    }
}
