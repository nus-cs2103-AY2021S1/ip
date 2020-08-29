package duke;

import duke.exception.DukeException;
import duke.operation.ExitOperation;
import duke.operation.Operation;
import duke.operation.StartOperation;
import duke.parser.CommandParser;
import duke.storage.TaskStorage;
import duke.task.TaskList;

/**
 * Represents the main driver class of Duke.
 */
public class Duke {
    private final TaskStorage taskStorage;
    private final TaskList taskList;
    private final CommandParser commandParser;

    /**
     * Constructor method for Duke.
     */
    public Duke() {
        this.taskStorage = TaskStorage.createTaskStorage();
        this.taskList = new TaskList();
        this.commandParser = new CommandParser();
    }

    /**
     * Initialises Duke by running a <code>StartOperation</code>.
     * @return status of the <code>StartOperation</code>.
     */
    public String initialize() {
        return new StartOperation(this.taskList, this.taskStorage).execute();
    }

    /**
     * Stops Duke by running a <code>ExitOperation</code>.
     * @return status of the <code>ExitOperation</code>.
     */
    public String stopDuke() {
        return new ExitOperation(this.taskStorage, this.taskList).execute();
    }

    /**
     * Runs an associated <code>Operation</code> based on <code>input</code>.
     * @param input the <code>String</code> the user inputs.
     * @return a <code>String</code> of the status of the executed <code>Operation</code>.
     */
    public String getResponse(String input) {
        try {
            Operation operation = this.commandParser.parse(input, this.taskList, this.taskStorage);
            return operation.execute();
        } catch (DukeException exception) {
            return exception.getMessage();
        }
    }
}
