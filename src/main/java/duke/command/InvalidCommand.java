package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * represents a command when the input of the user is invalid
 */
public class InvalidCommand extends Command {

    /**
     * class constructor
     */
    public InvalidCommand() {
        super("");
        this.isExit = false;
    }

    /**
     * returns a message indicating that the given command cannot be understood
     * @param tasks the list of tasks
     * @param ui the user interface object responsible for system related commands
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating that the given command cannot be understood
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "oops! im sorry, but i do not know what that means :-(";
    }
}
