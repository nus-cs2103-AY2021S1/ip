package main.java;

/**
 * Encapsulates exceptions that may be thrown when Bob runs.
 */
public class BobException extends Exception {

    /**
     * Returns an error message.
     *
     * @return an error message.
     */
    public String getMessage() {
        return "Sorry, but I do not understand your request. Please try again.";
    }
}
