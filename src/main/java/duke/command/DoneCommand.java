package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidIndexException;

import java.io.IOException;

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
        this.index = index;
    }

    /**
     *
     * @param tasks The TaskList for Duke.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return True because Duke should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markDone(index);
            storage.save(tasks);
            ui.showMarkDone(tasks.get(index));
        } catch (DukeInvalidIndexException | IOException e) {
            ui.showError(e);
        }
        return true;
    }
}
