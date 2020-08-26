package command;

import exception.InvalidIndexException;
import function.DukeFunction;
import task.Task;
import ui.UIPrint;
import data.DukeData;

public class DoneCommand extends Command {

    public DoneCommand() {
        names = new String[] { "done" };
    }

    @Override
    public void execute(String str) throws InvalidIndexException {
        boolean canParseInt = tryParseInt(str);
        int taskIndex = canParseInt ? Integer.parseInt(str) - 1 : -1;

        checkException(taskIndex, str);

        Task task = DukeData.tasks.get(taskIndex);
        task.markAsDone();

        DukeFunction.reportDoneTask(task);
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
        if (DukeData.tasks.size() <= taskIndex || taskIndex < 0) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nSorry " + str + " is not a valid index\n" + line;
            throw new InvalidIndexException(errMessage);
        }
    }
}
