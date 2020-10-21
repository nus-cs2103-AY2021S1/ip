package duke.exception;

/**
 * The exception to be thrown when Duke encounters index illegal or out of range in a done command.
 */
public class IllegalDoneArgument extends Exception {
    /**
     * Construct a new exception to indicate illegal index for delete.
     */
    public IllegalDoneArgument() {
        super("☹ OOPS!!! The argument for a done command must be an integer within the range!!!");
    }
}
