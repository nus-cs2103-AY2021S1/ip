package Command;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

/**
 * ListCommand will print out the TaskList for the user.
 * @author Joshua
 */
public class ListCommand extends Command {

    /**
     * Returns true if command terminates Duke.
     * @return the boolean to continue Duke.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the ListCommand by sending the TaskList to be printed to the Ui.
     * The Ui will then display the TaskList to the user. TaskList and Storage will
     * not be affected.
     * @param taskList the TaskList to be updated.
     * @param ui the Ui that interacts with the user.
     * @param storage the Storage that is updated with TaskList.
     * @throws DukeException throws exception when list is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        if (taskList.getTaskList().isEmpty()) {
            throw new DukeException("☹ OOPS !!! La lista está vacía. ¡Agregue una nueva tarea!");
        }
        ui.showList(taskList);
    }
}
