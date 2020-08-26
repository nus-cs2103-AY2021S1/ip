package command;

import exception.InvalidIndexException;
import task.Task;
import ui.UIPrint;
import ui.Ui;
import data.DukeData;

public class DeleteCommand extends Command {

    public DeleteCommand() {
        names = new String[] { "delete" };
    }

    @Override
    public void execute(String str) throws InvalidIndexException {
        boolean canParseInt = tryParseInt(str);
        int taskIndex = canParseInt ? Integer.parseInt(str) - 1 : -1;

        checkException(taskIndex, str);

        Task task = DukeData.tasks.remove(taskIndex);

        Ui.reportDeleteTask(task);
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
