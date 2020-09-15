package duke.error;

/**
 * Represents an error that occurs when the user passes in a negative number when using the
 * delete command.
 *
 * @author Roger Lim
 */
public class DeleteNegativeIndex extends Exception {
    public DeleteNegativeIndex() {
        super("    Please give a valid number");
    }
}
