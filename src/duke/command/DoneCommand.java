package duke.command;

import duke.data.DukeTaskList;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.ui.UIPrint;
import duke.ui.Ui;

public class DoneCommand extends Command {

    public DoneCommand() {
        names = new String[] { "done" };
    }

    @Override
    public void execute(String str) throws InvalidIndexException {
        boolean canParseInt = tryParseInt(str);
        int taskIndex = canParseInt ? Integer.parseInt(str) - 1 : -1;

        checkException(taskIndex, str);

        Task task = DukeTaskList.tasks.get(taskIndex);
        task.markAsDone();

        Ui.reportDoneTask(task);
    }

    private boolean tryParseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private void checkException(int taskIndex, String str) {
        if (DukeTaskList.tasks.size() <= taskIndex || taskIndex < 0) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nSorry " + str + " is not a valid index\n" + line;
            throw new InvalidIndexException(errMessage);
        }
    }
}
