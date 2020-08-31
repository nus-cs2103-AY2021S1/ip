package duke.exception;

/**
 * Indicates when a user's input date is not recognized by the program due to a non-existent date 
 * or time or when the format of input date is not recognized by the program.
 */
public class InvalidTaskDateException extends InvalidUserCommandException{
    /**
     * Creates a new InvalidTaskDateException with the specified invalid date.
     *
     * @param invalidDate
     */
    public InvalidTaskDateException(String invalidDate) {
        super("OOPS! " + invalidDate + " does not exist."
                + "\nPlease check to ensure that the date and time are correct.");
    }
}
