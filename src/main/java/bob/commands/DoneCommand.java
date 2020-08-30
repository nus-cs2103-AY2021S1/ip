package bob.commands;

import java.io.IOException;

import bob.data.task.Task;
import bob.data.task.Tasklist;
import bob.exceptions.BobInvalidNumberException;
import bob.exceptions.BobListIndexOutOfBoundsException;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Marks a task done from Bob's tasklist.
 */
public class DoneCommand extends Command {
    private final String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean isExited() {
        return false;
    }

    /**
     * Executes done command.
     *
     * @param tasks Bob's tasklist.
     * @param ui Bob's ui.
     * @param storage Bob's storage.
     * @throws BobInvalidNumberException If input cannot be parsed.
     * @throws BobListIndexOutOfBoundsException If number > size of tasklist or <= 0.
     * @throws IOException If an error occurs while updating file.
     */
    @Override
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
