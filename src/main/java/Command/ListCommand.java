package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand will print out the TaskList for the user.
 *
 * @author Joshua
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by sending the TaskList to be printed to the Ui.
     * The Ui will then display the TaskList to the user. TaskList and Storage will
     * not be affected.
     *
     * @param taskList the TaskList to be updated.
     * @param ui the Ui that interacts with the user.
     * @param storage the Storage that is updated with TaskList.
     * @throws DukeException throws exception when list is empty.
     * @return output to be displayed to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.getTaskList().isEmpty()) {
            throw new DukeException("OOPS !!! La lista esta vacia. Agregue una nueva tarea!");
        }
        output = ui.showList(taskList);
        return output;
    }
}
