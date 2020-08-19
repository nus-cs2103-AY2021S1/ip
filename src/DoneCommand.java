import exception.InvalidIndexException;

public class DoneCommand extends Command {

    public DoneCommand() {
        names = new String[] { "done" };
    }

    @Override
    public void execute(String str) throws InvalidIndexException {
        UIPrint.drawLine(UIPrint.star, 50);

        boolean canParseInt = tryParseInt(str);
        int taskIndex = canParseInt ? Integer.parseInt(str) - 1 : -1;

        if (Duke.tasks.size() <= taskIndex || taskIndex < 0) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nSorry " + str + " is not a valid index\n" + line;
            throw new InvalidIndexException(errMessage);
        } else {
            Task task = Duke.tasks.get(taskIndex);
            task.markAsDone();
            System.out.println("Nice, I've marked this task as done:");
            System.out.println(task);
        }

        UIPrint.drawLine(UIPrint.star, 50);
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
