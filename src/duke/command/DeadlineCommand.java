package duke.command;

import duke.Duke;
import duke.task.Deadline;

public class DeadlineCommand extends Command {

    public DeadlineCommand() {
        names = new String[] { "deadline" };
    }

    @Override
    public void execute(String str, Duke duke) {
        Deadline newDeadline = Deadline.createDeadline(str);
        duke.taskList.tasks.add(newDeadline);
        duke.ui.reportNewTask(newDeadline);
    }
}
