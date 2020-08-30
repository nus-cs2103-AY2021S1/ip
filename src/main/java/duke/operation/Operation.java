package duke.operation;

import duke.result.Result;

/**
 * Represents instructions to execute commands.
 */
public abstract class Operation {
    /**
     * Checks is the <code>Operation</code> is an <code>ExitOperation</code>.
     * @return <code>true</code> if the <code>Operation</code> is an <code>ExitOperation</code>.
     */
    public abstract boolean isExit();

    /**
     * Executes the series of instructions.
     * @return a <code>Result</code> containing the status of the execution.
     */
    public abstract Result execute();
}
