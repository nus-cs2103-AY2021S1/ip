package command;

import task.Deadline;
import data.DukeData;
import ui.Ui;

public class DeadlineCommand extends Command {

    public DeadlineCommand() {
        names = new String[] { "deadline" };
    }

    @Override
    public void execute(String str) {
        Deadline newDeadline = Deadline.createDeadline(str);
        DukeData.tasks.add(newDeadline);
        Ui.reportNewTask(newDeadline);
    }
}
