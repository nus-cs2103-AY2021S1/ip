/**
 * An Exception that should be thrown when malformed input is received.
 *
 * @author jingyenloh
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
