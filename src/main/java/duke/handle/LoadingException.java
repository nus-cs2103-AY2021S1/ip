package main.java.duke.handle;

/**
 * The LoadingException class describes the exception where
 * the local record cannot be read by the storage manager.
 */
public class LoadingException extends Exception {

    /**
     * Takes in the message of why the record cannot be read and
     * returns an exception.
     *
     * @param message The message of why the record cannot be read.
     */
    public LoadingException(String message) {
        super(message);
    }
}
