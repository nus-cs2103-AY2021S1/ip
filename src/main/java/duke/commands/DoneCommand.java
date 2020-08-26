package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeAlreadyDoneException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidIndexException;
import duke.task.Task;

/**
 * Handles the marking of a task as done in the chatbot.
 */
public class DoneCommand implements Command {
    public Integer index;

    /**
     * DoneCommand constructor.
     * @param index The index of the task in the array to be marked as done.
     */
    public DoneCommand(Integer index) {
        this.index = index;
    }

    /**
     * Executes the marking of task as done and sends the appropriate response to the user.
     * It also stores the changes to the storage.
     * @param tasks TaskList.
     * @param ui Ui.
     * @param storage Storage.
     * @return A boolean to indicate that it is not the terminating command.
     * @throws DukeException Exceptions when executing the different methods of TaskList,
     * Ui and Storage. Also accounts for index errors.
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= tasks.getSize() || index < 0) {
            throw new DukeInvalidIndexException();
        }
        Task currentTask = tasks.getTask(index);

        if (currentTask.isDone) {
            throw new DukeAlreadyDoneException();
        }
        tasks.markAsDone(index);
        ui.doneMessage(currentTask);
        storage.save(tasks.getPlanner());
        return false;
    }
}
