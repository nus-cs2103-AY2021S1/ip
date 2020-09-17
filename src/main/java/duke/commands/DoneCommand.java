package duke.commands;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;
import duke.tasks.TaskList;
import duke.tasks.Task;

import java.io.IOException;

/**
 * commands.Command that marks a Task as completed and updates the storage.
 */
public class DoneCommand extends Command {
    int index;

    /**
     * Constructor.
     * @param index index integer of the Tasklist containing the Task to be marked done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Mark a Task as done in the TaskList an d update the file.
     * @param taskList TaskList containing Tasks.
     * @param ui Ui that handles system output.
     * @param storage Storage that handles file saving.
     * @return outputString Command output.
     * @throws IOException If file don't exist.
     * @throws DukeException If input is not recognised.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        if (index >= taskList.size() || index < 0) {
            throw new DukeException("Sorry there's no such index for your task :(");
        }
        Task doneTask = taskList.setDoneTask(index);
        String outputString = ui.printDoneMessage(doneTask);
        outputString += super.execute(taskList, ui, storage);
        return outputString;
    }

}
