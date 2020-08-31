package duke;

import duke.exception.DukeException;
import duke.operation.ExitOperation;
import duke.operation.Operation;
import duke.operation.StartOperation;
import duke.parser.CommandParser;
import duke.result.Result;
import duke.storage.TaskStorage;
import duke.task.TaskList;
import duke.ui.CommandLineUi;

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
     *
     * @return status of the <code>StartOperation</code>.
     */
    public Result initialize() {
        return new StartOperation(this.taskList, this.taskStorage).execute();
    }

    /**
     * Stops Duke by running a <code>ExitOperation</code>.
     *
     * @return status of the <code>ExitOperation</code>.
     */
    public Result stopDuke() {
        return new ExitOperation(this.taskStorage, this.taskList).execute();
    }

    /**
     * Runs an associated <code>Operation</code> based on <code>input</code>.
     *
     * @param input the <code>String</code> the user inputs.
     * @return a <code>String</code> of the status of the executed <code>Operation</code>.
     */
    public Result getResponse(String input) {
        try {
            Operation operation = this.commandParser.parse(input, this.taskList, this.taskStorage);
            return operation.execute();
        } catch (DukeException exception) {
            return new Result(false, exception.getMessage(), false);
        }
    }

    /**
     * Drives the execution of <code>Duke</code> in the CLI.
     * It handles any exceptions thrown by printing them onto the <code>CommandLineUi</code>.
     */
    public void runDuke() {
        CommandLineUi ui = new CommandLineUi();
        String initializeStatus = initialize().getMessage();

        ui.showStartMessage();
        ui.showLoadStatus(initializeStatus);

        boolean isExit = false;
        while (!isExit) {
            String command = ui.readUserInput();
            try {
                Operation operation = this.commandParser.parse(command, this.taskList, this.taskStorage);
                String status = operation.execute().getMessage();
                ui.showStatus(status);
                isExit = operation.isExit();
            } catch (DukeException exception) {
                ui.showStatus(exception.getMessage());
            }
        }
    }

    /**
     * Driver method for the running of Duke in the CLI.
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
