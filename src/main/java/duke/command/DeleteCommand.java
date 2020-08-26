package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {

    /**
     * A string array that represents the instruction for this command.
     */
    private String[] nextCommandArr;

    public DeleteCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    /**
     * Deletes a Task from the taskList.
     * @param tasks is the list of tasks stored by Duke.
     * @param ui is the user interface to read inputs from the user and print messages.
     * @param storage deals with saving tasks into the file and loading tasks
     *                from the file.
     *
     * @throws DukeException if an invalid task number is provided to be deleted.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int deleteTaskRef = Integer.parseInt(this.nextCommandArr[1]);
            Task deleteTask = tasks.get(deleteTaskRef - 1);
            tasks.remove(deleteTaskRef - 1);
            ui.deleteTaskText(deleteTask, tasks);
        } catch (Exception e) {
            throw new DukeException("Please provide a valid task number to delete~");
        }
    }

    /**
     * Indicates Duke should keep running after this command is executed.
     * @return true.
     */
    @Override
    public boolean continueRunning() {
        return true;
    }
}
