package bob.exception;

/**
 * An exception which may be thrown when a key words are not provided.
 */
public class BobFindNoKeyWordsException extends BobException {

    /**
     * Returns a message to indicate that a key word was not provided.
     * @return a message to indicate that a key word was not provided.
     */
    @Override
    public String getMessage() {
        return "A key word or phrase was not provided. "
                + "\nPlease provide the key word or phrase that you're trying to find.";
    }
}
