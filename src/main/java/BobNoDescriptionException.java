package main.java;

/**
 * An exception to be thrown when a description is not provided.
 */
public class BobNoDescriptionException extends BobException {

    /**
     * Returns a message that indicates a description has not been provided.
     *
     * @return a message that indicates a description has not been provided.
     */
    @Override
    public String getMessage() {
        return "Please include a description for this todo!";
    }
}
