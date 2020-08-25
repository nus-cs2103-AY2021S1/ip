package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Lists the tasks in TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * @param taskList TaskList associated with command.
     * @param ui Ui associated with command.
     * @param storage Storage associated with command.
     * @throws DukeException If there is error during execution of command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listTasks(taskList);
    }

    /**
     * Indicates whether Duke chatbot is still running.
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}
