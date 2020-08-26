package duke.command;

import duke.data.DukeTaskList;
import duke.task.Deadline;
import duke.ui.Ui;

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
