package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * A Class to show the user the list of tasks that they have.
 */
public class ListCommand extends Command {

    /**
     * The constructor to display a list.
     * @param command the specific command instructions
     */
    public ListCommand(String command) {
        super(command, false);

    }

    /**
     * Executes the command to show all the tasks from the list of tasks.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.tasks(list);
    }

    /**
     * Executes the command to show all the tasks from the list of tasks on FXML.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    @Override
    public String executeChat(TaskList list, Ui ui, Storage storage) {
        return ui.tasks(list, true);
    }
}
