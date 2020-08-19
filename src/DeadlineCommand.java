import exception.IncorrectFormatException;

public class DeadlineCommand extends Command {

    public DeadlineCommand() {
        names = new String[] { "deadline" };
    }

    @Override
    public void execute(String str) {
        String[] splitStr = str.split(" /by ", 2);

        if (splitStr.length != 2) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nPlease follow the format of deadline <task description> /by <deadline>\n" + line;
            throw new IncorrectFormatException(errMessage);
        }

        String description = splitStr[0];
        String deadline = splitStr[1];

        Deadline newDeadline = new Deadline(UIPrint.deadlineIcon, description, deadline);
        Duke.tasks.add(newDeadline);
        Duke.reportNewTask(newDeadline);
    }
}
