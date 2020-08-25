package seedu.bob.commands;

import seedu.bob.data.task.Task;
import seedu.bob.data.task.Tasklist;

import seedu.bob.exceptions.BobEmptyTaskException;

import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;

import java.io.IOException;

/**
 * Adds a task to Bob's tasklist.
 */
public class TodoCommand extends Command {
    private final String input;

    /**
     * Creates a todo command.
     *
     * @param input User input.
     * @throws BobEmptyTaskException If there is no description for task.
     */
    public TodoCommand(String input) throws BobEmptyTaskException {
        if (input.length() == 0) {
            throw new BobEmptyTaskException();
        }

        this.input = input.startsWith(" ")
                    ? input.substring(1)
                    : input;
    }


    @Override
    public boolean isExited() {
        return false;
    }

    /**
     * Executes todo command.
     *
     * @param tasks Bob's tasklist.
     * @param ui Bob's ui.
     * @param storage Bob's storage.
     * @throws IOException If an error occurs while updating file.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) throws IOException {
        Task task = new Task(this.input);
        tasks.addTask(task);
        tasks.updateData(storage);
        ui.showAddMessage(task, tasks);
    }
}
