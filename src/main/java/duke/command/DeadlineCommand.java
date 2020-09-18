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
        description = "Creates a deadline task.";
        format = CommandFormat.DEADLINE_CMD_FORMAT;
    }

    /**
     * Creates a Deadline task, stores it in task list, reports to the user.
     * @param str the task info
     * @param duke the current Duke
     */
    @Override
    public void execute(String str, Duke duke) {
        Deadline newDeadline = Deadline.createDeadline(str);
        duke.getTaskList().addTask(newDeadline);
        response(newDeadline, duke);
    }

    private void response(Deadline newDeadline, Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportNewTask(newDeadline);
        } else {
            duke.getUiResponse().reportNewTask(newDeadline);
        }
    }
}
