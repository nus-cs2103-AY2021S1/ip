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
     * @param taskList is the list of tasks stored by Duke.
     * @param ui is the user interface to read inputs from the user and print messages.
     * @param storage deals with saving tasks into the file and loading tasks
     *                from the file.
     *
     * @throws DukeException if an invalid task number is provided to be marked as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int doneTaskRef = Integer.parseInt(this.nextCommandArr[1]);
            Task doneTask = tasks.get(doneTaskRef - 1);
            doneTask.setDone();
            ui.doneText(doneTask);
        } catch (Exception e) {
            throw new DukeException("Please input a valid task number~");
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
