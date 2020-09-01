package duke.commands;

import duke.*;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ui.showDeleteMessage(tasks, idx);
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("cannot save data due to unexpected error lah.");
        }
    }
}
