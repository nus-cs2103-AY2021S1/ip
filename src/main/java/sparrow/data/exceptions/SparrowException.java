package sparrow.data.exceptions;

public class SparrowException extends Exception {
    /**
     * Returns emoji to be pre-pended to an exception message.
     * @return Pre-exception string.
     */
    public static String standardExceptionMessage() {
        return "ARR!\uD83C\uDFF4\u200D\u2620\uFE0F️ ";
    }

    public SparrowException(String message, Throwable cause) {
        super(message, cause);
    }

}
