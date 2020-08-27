package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.Ui;
import duke.task.Task;
import duke.task.TaskList;

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
     * @param ui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     * @throws DukeException exception thrown when exception caught while running.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        try {
            String taskNumberToDelete = this.fullCommand.substring(7);
            int index = Integer.parseInt(taskNumberToDelete) - 1;
            Task deleteTask = tasklist.getItem(index);
            tasklist.deleteItem(index);
            ui.deleteMessage(deleteTask, tasklist.getTasksLeft());

            // change data file
            storage.deleteTask(index);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a task number within the range of tasks");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me to delete");
        }
    }
}
