package duke.command;

import duke.exception.DukeOutOfBoundsException;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a done command.
 */
public class DoneCommand implements Command {
    private int index;

    public DoneCommand(int index) {
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
            throw new DukeOutOfBoundsException(CommandKey.DONE.getKey() + " " + index);
        }
    }

    /**
     * Marks the specified task in the user's task list as done.
     *
     * @param tasks List of user's tasks.
     * @param ui UI of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            checkIndex(tasks);
            Task task = tasks.get(index);
            task.setDone();
            ui.displayDoneMessage(task);
        } catch (DukeOutOfBoundsException e) {
            ui.displayError(e.toString());
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
