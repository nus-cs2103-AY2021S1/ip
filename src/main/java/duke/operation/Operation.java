package duke.operation;

/**
 * Represents the series of operations that are to be executed.
 */
public abstract class Operation {
    /**
     * Checks is the Operation is an ExitOperation.
     * @return true if the Operation is an Exit Operation.
     */
    public abstract boolean isExit();

    /**
     * Executes the series of operations.
     * @return a String containing the status of the execution.
     */
    public abstract String execute();
}