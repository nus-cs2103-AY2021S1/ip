package main.java;

/**
 * An exception to be thrown when trying to access a file which does not exist.
 */
public class BobIOException extends BobException {

    /**
     * Returns a message to indicate that information could not be saved to a file.
     * @return a message to indicate that information could not be saved to a file.
     */
    @Override
    public String getMessage() {
        return "Sorry, but I had difficulty saving your information. Please try again.";
    }
}
