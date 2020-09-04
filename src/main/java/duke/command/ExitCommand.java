package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * represents a command to exit the application
 */
public class ExitCommand extends Command {

    /**
     * class constructor
     */
    public ExitCommand() {
        super("bye");
        this.isExit = true;
    }

    /**
     * causes the application to close.
     * @param tasks the list of tasks
     * @param ui the user interface object responsible for system related commands
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating the application has been successfully shut down
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        return ui.exit();
    }
}
