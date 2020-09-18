package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {

    /**
     * A string array that represents the instructions in this command.
     */
    private String[] nextCommandArr;
    public DoneCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    /**
     * Marks a task in the taskList as done.
     * @param tasks is the list of tasks stored by Duke.
     * @param ui is the user interface to read inputs from the user and print messages.
     * @param storage deals with saving tasks into the file and loading tasks
     *                from the file.
     *
     * @return A string indicating the task marked as done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int doneTaskRef = Integer.parseInt(this.nextCommandArr[1]);
            Task doneTask = tasks.get(doneTaskRef - 1);
            doneTask.setDone();
            return ui.doneText(doneTask);
        } catch (Exception e) {
            return new DukeException("Tell grandpa which task you completed.").toString();
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
