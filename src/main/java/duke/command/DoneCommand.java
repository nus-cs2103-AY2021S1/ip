package duke.command;

import duke.Duke;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.ui.UIPrint;

/**
 * DoneCommand marks the task with the index as done, reports to the user.
 */
public class DoneCommand extends Command {

    /**
     * Constructs a DoneCommand.
     */
    public DoneCommand() {
        names = new String[] { "done" };
    }

    /**
     * Marks the task with the index as done, reports to the user.
     * @param str the index of the task
     * @param duke the current Duke
     * @throws InvalidIndexException thrown when the index is invalid
     */
    @Override
    public void execute(String str, Duke duke) throws InvalidIndexException {
        boolean canParseInt = tryParseInt(str);
        int taskIndex = canParseInt ? Integer.parseInt(str) - 1 : -1;

        checkException(taskIndex, str, duke);

        Task task = duke.taskList.getTask(taskIndex);
        task.markAsDone();

        duke.ui.reportDoneTask(task);
    }

    private boolean tryParseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private void checkException(int taskIndex, String str, Duke duke) {
        if (duke.taskList.getSize() <= taskIndex || taskIndex < 0) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nSorry " + str + " is not a valid index\n" + line;
            throw new InvalidIndexException(errMessage);
        }
    }
}
