package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list all tasks the user has.
 */
public class ListCommand extends Command {

    /**
     * Returns false since this is not an exit command.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command by obtaining all tasks from taskList and displaying
     * them to the user.
     * @param taskList the list of tasks user has
     * @param ui ui instance to display messages
     * @param storage storage instance to manage updating on disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.listTasks(taskList);
    }
}
