package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeAlreadyDoneException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidIndexException;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Handles the marking of a task as done in the chatbot.
 */
public class DoneCommand implements Command {
    private Integer index;

    /**
     * DoneCommand constructor.
     *
     * @param index The index of the task in the array to be marked as done.
     */
    public DoneCommand(Integer index) {
        this.index = index;
    }

    /**
     * Executes the marking of task as done and sends the appropriate response to the user.
     * It also stores the changes to the storage.
     *
     * @param tasks   TaskList.
     * @param ui      Ui.
     * @param storage Storage.
     * @return The done message by the Ui.
     * @throws DukeException Exceptions when executing the different methods of TaskList,
     *                       Ui and Storage. Also accounts for index errors.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
        if (index >= tasks.getSize() || index < 0) {
            throw new DukeInvalidIndexException();
        }
        Task currentTask = tasks.getTask(index);

        if (currentTask.isDone()) {
            throw new DukeAlreadyDoneException();
        }
        tasks.markAsDone(index);
        String reply = ui.doneMessage(currentTask);
        storage.save(tasks.getPlanner());
        return reply;
    }
}
