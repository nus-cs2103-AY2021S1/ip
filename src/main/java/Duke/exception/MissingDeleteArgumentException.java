package duke.exception;

/**
 * Thrown when issued delete input hs no argument.
 */
public class MissingDeleteArgumentException extends Exception {

    /**
     * Constructs the exception.
     */
    public MissingDeleteArgumentException() {
        super("\uD83D\uDE41 OOPS! You have to specify which task you want to delete.");
    }

    /**
     * String representation of this exception.
     * @return  String representation of this exception.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
