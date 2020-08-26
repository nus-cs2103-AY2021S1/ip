package main.java.Exception;

/**
 * Exception thrown when user failed to specify the keyword
 * in command.
 */
public class DukeKeywordException extends DukeException {

    /**
     * Returns the exception message to the user.
     * The message is "OOPS!! Keyword is not specified."
     *
     * @return String exception message
     */
    @Override
    public String toString() {
        return super.toString() + " Keyword is not specified.";
    }
}
