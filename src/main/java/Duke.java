import exception.DukeException;
import operation.Operation;
import parser.CommandParser;
import task.TaskList;
import storage.TaskStorage;
import ui.Ui;

public class Duke {
    private TaskStorage taskStorage;
    private TaskList taskList;
    private Ui ui;
    private CommandParser commandParser;

    private void initialiseDuke() {
        this.ui = new Ui();
        this.ui.showStartMessage();
        this.taskStorage = TaskStorage.createTaskStorage();
        try {
            this.taskList = this.taskStorage.loadTaskList();
        } catch (DukeException exception) {
            this.taskList = new TaskList();
            this.ui.showStatus(exception.getMessage());
        }
        this.commandParser = new CommandParser();
    }

    public void runDuke() {
        initialiseDuke();

        boolean isExit = false;
        while (!isExit) {
            this.ui.showBlankLine();
            String command = this.ui.readUserInput();

            this.ui.showDivider();
            try {
                Operation operation = this.commandParser.parse(command, this.taskList, this.taskStorage);
                String status = operation.execute();
                this.ui.showStatus(status);
                isExit = operation.isExit();
            } catch (DukeException exception) {
                this.ui.showStatus(exception.getMessage());
            }
            this.ui.showDivider();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
