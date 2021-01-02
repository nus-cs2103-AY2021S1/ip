package willy.exceptions;

import willy.ui.Willy;

/**
 * Handles the errors that may arise in the process of using the bot.
 */
public class WillyException extends Exception {
    private String error;

    /**
     * Construct a WillyException with the input of the type of error in String form
     * @param errorMessage Type of Error that occurred
     */
    public WillyException(String errorMessage) {
        super(errorMessage);
        this.error = errorMessage;
    }

    @Override
    public String toString() {
        return Willy.response(error);
    }
}
