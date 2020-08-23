package taskbot.exceptions;

/**
 * This exception handles when the user tries to access a
 * task from the list but gets IndexOutOfBounds error
 */

public class InvalidIndexException extends Exception {
    public InvalidIndexException(String msg) {
        super(msg);
    }
}
