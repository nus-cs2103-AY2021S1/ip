package duke.exception;

/**
 * Exception to be thrown when Duke encounters an index illegal or out of range in a delete command.
 */
public class IllegalDeleteArgument extends Exception {
    /**
     * Construct the exception to indicate illegal index.
     */
    public IllegalDeleteArgument() {
        super("☹ OOPS!!! The argument for a delete command must be an integer within the range!!!");
    }
}