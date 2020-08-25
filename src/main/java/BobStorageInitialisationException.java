package main.java;

/**
 * An exception to be thrown when an error occurs during Storage initialisation.
 */
public class BobStorageInitialisationException extends BobException {

    /**
     * Returns a message to indicate that the Storage could not be initialised.
     * @return a message to indicate that the Storage could not be initialised.
     */
    @Override
    public String getMessage() {
        return "Could not initialise storage";
    }
}
