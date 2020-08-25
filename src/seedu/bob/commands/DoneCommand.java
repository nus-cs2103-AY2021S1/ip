package seedu.bob.commands;

import seedu.bob.data.task.Task;
import seedu.bob.data.task.Tasklist;

import seedu.bob.exceptions.BobInvalidNumberException;
import seedu.bob.exceptions.BobListIndexOutOfBoundsException;

import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {
    private final String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean isExited() {
        return false;
    }

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
