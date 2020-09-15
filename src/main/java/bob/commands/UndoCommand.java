package bob.commands;

import java.io.IOException;

import bob.common.Messages;
import bob.data.task.Tasklist;
import bob.storage.Storage;

/**
 * Undoes the changes executed by the most recent command given to Bob.
 */
public class UndoCommand extends Command {

    public boolean isExited() {
        return false;
    }

    /**
     * Executes undo command.
     *
     * @param tasks Bob's tasklist.
     * @param storage Bob's storage.
     * @return String message response after executing undo command.
     * @throws IOException If an error occurs while updating file.
     */
    public String execute(Tasklist tasks, Storage storage) throws IOException {
        tasks.updateData(storage);
        return Messages.UNDO_MSG;
    }
}
