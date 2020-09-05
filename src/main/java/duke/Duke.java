package duke;

import duke.exception.DukeException;
import duke.list.ListManager;
import duke.operation.ExitOperation;
import duke.operation.Operation;
import duke.operation.StartOperation;
import duke.parser.CommandParser;
import duke.result.Result;
import duke.storage.TaskStorage;

/** Represents the main driver class of Duke. */
public class Duke {
    private final TaskStorage taskStorage;
    private final ListManager listManager;
    private final CommandParser commandParser;

    /**
     * Constructor method for Duke.
     */
    public Duke() {
        this.taskStorage = TaskStorage.createTaskStorage();
        this.listManager = new ListManager();
        this.commandParser = new CommandParser();
    }

    /**
     * Initialises Duke by running a <code>StartOperation</code>.
     *
     * @return status of the <code>StartOperation</code>.
     */
    public Result initialize() {
        return new StartOperation(this.listManager.getTaskList(), this.taskStorage).execute();
    }

    /**
     * Stops Duke by running a <code>ExitOperation</code>.
     *
     * @return status of the <code>ExitOperation</code>.
     */
    public Result stopDuke() {
        return new ExitOperation(this.taskStorage, this.listManager.getTaskList()).execute();
    }

    /**
     * Runs an associated <code>Operation</code> based on <code>input</code>.
     *
     * @param input the <code>String</code> the user inputs.
     * @return a <code>String</code> of the status of the executed <code>Operation</code>.
     */
    public Result getResponse(String input) {
        try {
            Operation operation = this.commandParser.parse(input, this.listManager, this.taskStorage);
            return operation.execute();
        } catch (DukeException exception) {
            return new Result(false, exception.getMessage(), false);
        }
    }
}
