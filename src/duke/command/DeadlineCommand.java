package duke.command;

import duke.Duke;
import duke.task.Deadline;

/**
 * DeadlineCommand creates a Deadline task, stores it in task list, reports to the user.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs a DeadlineCommand.
     */
    public DeadlineCommand() {
        names = new String[] { "deadline" };
    }

    /**
     * Creates a Deadline task, stores it in task list, reports to the user.
     * @param str the task info
     * @param duke the current Duke
     */
    @Override
    public void execute(String str, Duke duke) {
        Deadline newDeadline = Deadline.createDeadline(str);
        duke.taskList.addTask(newDeadline);
        duke.ui.reportNewTask(newDeadline);
    }
}
