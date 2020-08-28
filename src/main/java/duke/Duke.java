package duke;

import duke.exception.DukeException;
import duke.operation.Operation;
import duke.operation.StartOperation;
import duke.parser.CommandParser;
import duke.task.TaskList;
import duke.storage.TaskStorage;
import duke.ui.Ui;

/**
 * Represents the main driver class of Duke.
 */
public class Duke {
    private final TaskStorage taskStorage;
    private final TaskList taskList;
    private final Ui ui;
    private final CommandParser commandParser;

    Duke() {
        this.ui = new Ui();
        this.taskStorage = TaskStorage.createTaskStorage();
        this.taskList = new TaskList();
        this.commandParser = new CommandParser();
    }

    private void initialiseDuke() {
        this.ui.showStartMessage();
        String status = new StartOperation(this.taskList, this.taskStorage).execute();
        this.ui.showLoadStatus(status);
    }

    /**
     * Drives the execution of <code>Duke</code>.
     * It handles any exceptions thrown by printing them onto the <code>Ui</code>.
     */
    public void runDuke() {
        initialiseDuke();
        boolean isExit = false;
        while (!isExit) {
            String command = this.ui.readUserInput();
            try {
                Operation operation = this.commandParser.parse(command, this.taskList, this.taskStorage);
                String status = operation.execute();
                this.ui.showStatus(status);
                isExit = operation.isExit();
            } catch (DukeException exception) {
                this.ui.showStatus(exception.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
