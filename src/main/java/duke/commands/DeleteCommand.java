package duke.commands;

import duke.*;
import duke.tasks.Task;

import java.io.IOException;

/**
 * Represents the delete command that removes a task from the task list
 */
public class DeleteCommand extends Command {
    private final int idx;

    /**
     * Initializes delete command with the input as the index from where the task should be deleted
     * @param i index of the task to be deleted
     */
    public DeleteCommand(int i) {
        idx = i;
    }

    /**
     * Deletes and then displays the task from the task list at the appropriate index
     * Then writes the updated task list to computer
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException { // TODO: FIX THIS
        try {
            Task t = tasks.remove(idx - 1);
            storage.save(tasks);
            return ui.showDeleteMessage(tasks, t);
        } catch (IOException e) {
            throw new DukeException("cannot save data due to unexpected error lah.");
        }
    }
}
