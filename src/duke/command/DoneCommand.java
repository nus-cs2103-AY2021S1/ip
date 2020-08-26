package duke.command;

import duke.Duke;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.ui.UIPrint;

public class DoneCommand extends Command {

    public DoneCommand() {
        names = new String[] { "done" };
    }

    @Override
    public void execute(String str, Duke duke) throws InvalidIndexException {
        boolean canParseInt = tryParseInt(str);
        int taskIndex = canParseInt ? Integer.parseInt(str) - 1 : -1;

        checkException(taskIndex, str, duke);

        Task task = duke.taskList.tasks.get(taskIndex);
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
        if (duke.taskList.tasks.size() <= taskIndex || taskIndex < 0) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nSorry " + str + " is not a valid index\n" + line;
            throw new InvalidIndexException(errMessage);
        }
    }
}
