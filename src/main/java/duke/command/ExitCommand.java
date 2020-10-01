package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import javafx.application.Platform;

/**
 * Represents command that is specific to the exit command.
 */
public class ExitCommand extends Command {

    /**
     * Creates an ExitCommand object.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Executes exit command of user.
     * @param taskList tasks of user.
     * @param ui user interface object.
     * @param storage Storage object to retrieve and store data from file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        assert taskList != null : "TaskList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";

        ui.setByeMessage();
        Platform.exit();
        System.exit(0);
    }
}
