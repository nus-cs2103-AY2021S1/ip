package duke.command;

import java.util.LinkedList;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Command that prints out the task list.
 */
public class ListCommand implements Command {

    /**
     * Returns whether this is an exit command.
     *
     * @return false at all times.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage,
                          LinkedList<ReversibleCommand> reversibleCommands) throws DukeException {
        return ui.displayList(taskList, "Here are the tasks in your list:");
    }
}
