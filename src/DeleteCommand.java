import exception.InvalidIndexException;

public class DeleteCommand extends Command {
    public DeleteCommand() {
        names = new String[] { "delete" };
    }

    @Override
    public void execute(String str) throws InvalidIndexException {
        boolean canParseInt = tryParseInt(str);
        int taskIndex = canParseInt ? Integer.parseInt(str) - 1 : -1;

        if (Duke.tasks.size() <= taskIndex || taskIndex < 0) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nSorry " + str + " is not a valid index\n" + line;
            throw new InvalidIndexException(errMessage);
        } else {
            Task task = Duke.tasks.remove(taskIndex);
            Duke.reportDeleteTask(task);
        }
    }

    private boolean tryParseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
