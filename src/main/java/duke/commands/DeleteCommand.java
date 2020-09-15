package duke.commands;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;
import duke.tasks.TaskList;
import duke.tasks.Task;

import java.io.IOException;

/**
 * commands.Command that deletes a task and updates the storage.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Constructor.
     * @param index index integer of the TaskList containing the Task to be removed.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Remove a Task to the TaskList an d update the file.
     * @param taskList TaskList containing Tasks.
     * @param ui Ui that handles system output.
     * @param storage Storage that handles file saving.
     * @throws IOException If file don't exist.
     * @throws DukeException If input is not recognised.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task deletedTask = taskList.remove(index);
        String outputString = ui.printDeleteMessage(deletedTask, taskList);
        outputString += super.execute(taskList, ui, storage);
        return outputString;
    }
}
