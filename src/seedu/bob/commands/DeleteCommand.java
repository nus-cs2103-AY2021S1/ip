package seedu.bob.commands;

import seedu.bob.data.task.Task;
import seedu.bob.data.task.Tasklist;
import seedu.bob.exceptions.BobInvalidNumberException;
import seedu.bob.exceptions.BobListIndexOutOfBoundsException;
import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;

import java.io.IOException;

/**
 * Deletes a task from Bob's tasklist
 */
public class DeleteCommand extends Command {
    String input;


    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean isExited() {
        return false;
    }

    /**
     * Executes delete command.
     * @param tasks Bob's tasklist.
     * @param ui Bo's ui.
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

            Task task = tasks.deleteTask(taskNum);
            ui.showDeleteMessage(task);
            tasks.updateData(storage);

        } catch (NumberFormatException e) {
            throw new BobInvalidNumberException();
        }
    }

}
