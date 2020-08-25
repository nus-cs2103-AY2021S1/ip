package command;

import exception.IncorrectFormatException;
import task.Deadline;
import ui.UIPrint;
import data.DukeData;
import function.DukeFunction;

public class DeadlineCommand extends Command {

    public DeadlineCommand() {
        names = new String[] { "deadline" };
    }

    @Override
    public void execute(String str) {
        Deadline newDeadline = Deadline.createDeadline(str);
        DukeData.tasks.add(newDeadline);
        DukeFunction.reportNewTask(newDeadline);
    }
}
