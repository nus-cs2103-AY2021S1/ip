package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * represents a command to clear the entire list of tasks
 */
public class ClearCommand extends Command {

    /**
     * class constructor
     */
    public ClearCommand() {
        super("clear");
        this.isExit = false;
    }

    /**
     * deletes all of the tasks in the given task list and updates this change to the storage
     * finally, the method returns a message indicating the operation was successful
     * @param tasks the list of tasks
     * @param ui the user interface object responsible for system related commands
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating that the task list was successfully cleared
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteAll();
        storage.save(tasks);
        return getClearMessage();
    }

    private String getClearMessage() {
        return "okie! all the tasks in your list have been cleared :-)";
    }
}
