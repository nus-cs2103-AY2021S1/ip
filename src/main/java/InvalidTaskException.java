/**
 * An Exception that should be thrown when invalid input is received, while trying to create a Task.
 *
 * @author jingyenloh
 */
public class InvalidTaskException extends InvalidInputException {
    public InvalidTaskException(String message) {
        super(message);
    }
}
