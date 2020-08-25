package seedu.bob.commands;

import seedu.bob.data.task.Task;
import seedu.bob.data.task.Tasklist;
import seedu.bob.exceptions.BobInvalidNumberException;
import seedu.bob.exceptions.BobListIndexOutOfBoundsException;
import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;

import java.io.IOException;

/**
 * Marks a task done from Bob's tasklist.
 */
public class DoneCommand extends Command {
    String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean isExited() {
        return false;
    }

    /**
     * Executes done command.
     * @param tasks Bob's tasklist.
     * @param ui Bob's ui.
     * @param storage Bob's storage.
     * @throws BobInvalidNumberException If input cannot be parsed.
     * @throws BobListIndexOutOfBoundsException If number > size of tasklist or <= 0.
     * @throws IOException If an error occurs while updating file.
     */
    public void execute(Tasklist tasks, Ui ui, Storage storage)
            throws BobInvalidNumberException, BobListIndexOutOfBoundsException, IOException {
        try {
            int taskNum = Integer.parseInt(input.replaceAll("\\s+", ""));
            if (taskNum > tasks.getListSize() || taskNum <= 0) {
                throw new BobListIndexOutOfBoundsException(tasks.getListSize(), taskNum, "mark");
            }

            Task task = tasks.markTaskDone(taskNum);
            tasks.updateData(storage);
            ui.showDoneMessage(task);
        } catch (NumberFormatException e) {
            throw new BobInvalidNumberException();
        }
    }
}
