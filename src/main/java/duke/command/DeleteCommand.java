package duke.command;

import duke.exception.DukeOutOfBoundsException;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Represents a delete task command.
 */
public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Checks if the index given to the command is valid.
     *
     * @param tasks List of user's tasks.
     * @throws DukeOutOfBoundsException If index is invalid.
     */
    private void checkIndex(TaskList tasks) throws DukeOutOfBoundsException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeOutOfBoundsException(CommandKey.DELETE.getKey() + " " + index);
        }
    }

    /**
     * Deletes the task from the user's task list.
     *
     * @param tasks List of user's tasks.
     * @param ui UI of Duke.
     * @return String of Duke's confirmation message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            checkIndex(tasks);
            Task removedTask = tasks.delete(index);
            return ui.displayDeletedTaskMessage(removedTask, tasks.size());
        } catch (DukeOutOfBoundsException e) {
            return ui.displayError(e.toString());
        }
    }

    /**
     * Tells Duke to continue running.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
