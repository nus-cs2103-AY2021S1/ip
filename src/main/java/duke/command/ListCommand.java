package duke.command;

import duke.component.*;

/**
 * Command that prints out the task list
 */
public class ListCommand implements Command {

    /**
     * Returns whether this is an exit command
     * @return false at all times
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out the task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.displayList(taskList);
    }
}
