package main.java;

/**
 * An exception which may be thrown when a desired file is cannot be found.
 */
public class BobFileNotFoundException extends BobException {

    /**
     * Returns a message to indicate that the file cannot be found.
     * @return a message to indicate that the file cannot be found.
     */
    @Override
    public String getMessage() {
        return "File not found.";
    }
}
