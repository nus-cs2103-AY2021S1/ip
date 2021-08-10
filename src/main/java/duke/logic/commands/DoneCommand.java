package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyListException;
import duke.exceptions.InvalidTaskNumberException;
import duke.logic.TaskList;
import duke.logic.tasks.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command by the user to mark a task in the list of tasks as done.
 * An DoneCommand object has a task number to represent the task to be marked done.
 */
public class DoneCommand extends Command {
    private int taskNum;

    /**
     * Instantiates a done command.
     *
     * @param taskNum The task number to be marked as done.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command by marking the specified task as done, replying the user,
     * and updating the list of tasks stored in the computer.
     *
     * @param tasks List of all the tasks of the user.
     * @param ui Replies the user.
     * @param storage Stores the new task in the computer.
     * @throws DukeException If there are no tasks, or an invalid task number is given.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null && ui != null && storage != null
                : "TaskList, Ui or Storage is not supposed to be null";

        if (tasks.getSize() == 0) {
            throw new EmptyListException();
        }

        if (this.taskNum == 0 || this.taskNum > tasks.getSize()) {
            throw new InvalidTaskNumberException();
        }

        Task t = tasks.getTask(this.taskNum);
        t.markDone();
        storage.updateData(tasks);
        return ui.showDone(t);
    }

    /**
     * Returns false as the command is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
