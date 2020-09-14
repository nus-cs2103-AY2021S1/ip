package duke.command;

import java.io.IOException;

import duke.backend.Storage;
import duke.task.TaskList;
import duke.response.Ui;
import duke.exception.DukeInvalidIndexException;

/**
 * Represents a marking a task as done action.
 */
public class DoneCommand implements Command {
    private final int index;

    /**
     * Class constructor with specified index.
     *
     * @param index The index of the Task to be marked as done.
     */
    public DoneCommand(int index) {
        assert(index >= 0);
        this.index = index;
    }

    /**
     * Returns false because command does not exit.
     *
     * @return false.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

    /**
     *
     * @param tasks The TaskList for Duke.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return True because Duke should continue running.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markDone(index);
            storage.save(tasks);
            return ui.showMarkDone(tasks.get(index));
        } catch (DukeInvalidIndexException | IOException e) {
            return ui.showError(e);
        }
    }
}
