package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidIndexException;
import duke.task.Task;

/**
 * Handles the deletion of a task in the chatbot.
 */
public class DeleteCommand implements Command {
    public Integer index;

    /**
     * DeleteCommand constructor.
     * @param index The index of the task in the array to be deleted.
     */
    public DeleteCommand(Integer index) {
        this.index = index;
    }

    /**
     * Executes the deletion of task and sends the appropriate response to the user.
     * It also stores the changes to the storage.
     * @param tasks TaskList.
     * @param ui Ui.
     * @param storage Storage.
     * @return A boolean to indicate that it is not the terminating command.
     * @throws DukeException Exceptions when executing the different methods of TaskList,
     * Ui and Storage.
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= tasks.getSize() || index < 0) {
            throw new DukeInvalidIndexException();
        }
        Task currentTask = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.deleteMessage(currentTask, tasks.getSize());
        storage.save(tasks.getPlanner());
        return false;
    }

}
