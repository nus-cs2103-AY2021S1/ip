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
     * @return A string indicating the task deleted.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int deleteTaskRef = Integer.parseInt(this.nextCommandArr[1]);
            Task deleteTask = tasks.get(deleteTaskRef - 1);
            tasks.remove(deleteTaskRef - 1);
            return ui.deleteTaskText(deleteTask, tasks);
        } catch (Exception e) {
            return new DukeException("Tell grandpa which task to delete.").toString();
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
