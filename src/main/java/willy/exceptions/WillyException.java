package willy.exceptions;

import willy.ui.Willy;

/**
 * Handles the errors that may arise in the process of using the bot.
 */
public class WillyException extends Exception {
    private String error;

    public WillyException(String errorMessage) {
        super(errorMessage);
        this.error = errorMessage;
    }

    @Override
    public String toString() {
        return Willy.response(error);
    }
}
