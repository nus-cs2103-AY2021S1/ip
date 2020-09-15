package bob.commands;

import java.io.IOException;

import bob.common.MsgGenerator;
import bob.data.task.Task;
import bob.data.task.Tasklist;
import bob.exceptions.BobInvalidNumberException;
import bob.exceptions.BobListIndexOutOfBoundsException;
import bob.storage.Storage;

/**
 * Deletes a task from Bob's tasklist
 */
public class DeleteCommand extends Command {
    private final String input;


    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean isExited() {
        return false;
    }


    /**
     * Executes delete command.
     *
     * @param tasks Bob's tasklist.
     * @param storage Bob's storage.
     * @return String message response after executing delete command.
     * @throws BobInvalidNumberException If input cannot be parsed.
     * @throws BobListIndexOutOfBoundsException If number > size of tasklist or <= 0.
     * @throws IOException If an error occurs while updating file.
     */

    @Override
    public String execute(Tasklist tasks, Storage storage)
            throws BobInvalidNumberException, BobListIndexOutOfBoundsException, IOException {
        try {
            int taskNum = Integer.parseInt(input.replaceAll("\\s+", ""));

            boolean isNegative = taskNum <= 0;
            boolean isOutOfBound = taskNum > tasks.getListSize();
            boolean isInvalidTaskNum = isNegative || isOutOfBound;
            boolean isValidTaskNum = !isInvalidTaskNum;

            if (isInvalidTaskNum) {
                throw new BobListIndexOutOfBoundsException(tasks.getListSize(), taskNum, "delete");
            }

            assert isValidTaskNum;
            Task task = tasks.deleteTask(taskNum);
            tasks.updateData(storage);
            return MsgGenerator.generateDeleteMessage(task);
        } catch (NumberFormatException e) {
            throw new BobInvalidNumberException();
        }
    }

}
