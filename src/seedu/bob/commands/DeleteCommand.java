package seedu.bob.commands;

import seedu.bob.data.task.Task;
import seedu.bob.data.task.Tasklist;
import seedu.bob.exceptions.BobInvalidNumberException;
import seedu.bob.exceptions.BobListIndexOutOfBoundsException;
import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean isExited() {
        return false;
    }
    public void execute(Tasklist tasks, Ui ui, Storage storage)
            throws BobInvalidNumberException, BobListIndexOutOfBoundsException {
        try {
            int taskNum = Integer.parseInt(input.replaceAll("\\s+", ""));
            if (taskNum > tasks.getListSize() || taskNum <= 0) {
                throw new BobListIndexOutOfBoundsException(tasks.getListSize(), taskNum, "mark");
            }

            Task task = tasks.deleteTask(taskNum);
            ui.showDeleteMessage(task);
            tasks.updateData(storage);

        } catch (NumberFormatException | IOException e) {
            throw new BobInvalidNumberException();
        }
    }

}
