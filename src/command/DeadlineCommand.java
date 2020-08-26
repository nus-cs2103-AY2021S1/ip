package command;

import data.DukeTaskList;
import task.Deadline;
import ui.Ui;

public class DeadlineCommand extends Command {

    public DeadlineCommand() {
        names = new String[] { "deadline" };
    }

    @Override
    public void execute(String str) {
        Deadline newDeadline = Deadline.createDeadline(str);
        DukeTaskList.tasks.add(newDeadline);
        Ui.reportNewTask(newDeadline);
    }
}
