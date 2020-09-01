package duke.command;

import duke.Gui;
import duke.component.DukeException;
import duke.component.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final String fullCommand;

    /**
     * Constructor for DeleteCommand.
     * @param fullCommand full input string from user.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes command, main logic for creating a new task.
     * @param tasklist list of tasks.
     * @param gui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     * @throws DukeException exception thrown when exception caught while running.
     */
    @Override
    public ArrayList<String> execute(TaskList tasklist, Gui gui, Storage storage, ArrayList<String> responseList) throws DukeException {
        try {
            String taskNumberToDelete = this.fullCommand.substring(7);
            int index = Integer.parseInt(taskNumberToDelete) - 1;
            Task deleteTask = tasklist.getItem(index);
            tasklist.deleteItem(index);
            // change data file
            storage.deleteTask(index);

            return gui.deleteMessage(deleteTask, tasklist.getTasksLeft());

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a task number within the range of tasks");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me to delete");
        }
    }
}
