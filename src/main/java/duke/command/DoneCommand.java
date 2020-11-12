package duke.command;

import java.io.IOException;

import duke.backend.Storage;
import duke.command.exception.DukeInvalidIndexException;
import duke.response.Response;
import duke.task.TaskList;

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
     *
     * @param tasks The TaskList for Duke.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return True because Duke should continue running.
     */
    @Override
    public String execute(TaskList tasks, Response ui, Storage storage) {
        try {
            tasks.markDone(index);
            storage.save(tasks);
            return ui.showMarkDone(tasks.get(index));
        } catch (DukeInvalidIndexException | IOException e) {
            return ui.showError(e);
        }
    }
}
