package sparrow.data.exceptions;

public class SparrowException extends Exception {

    /** Emoji to be pre-pended to an exception message.*/
    public static final String STANDARD_EXCEPTION_MESSAGE = "ARR!\uD83C\uDFF4\u200D\u2620\uFE0F️ ";

    public SparrowException(String message, Throwable cause) {
        super(message, cause);
    }
}
