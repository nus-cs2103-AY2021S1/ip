package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * This Command will mark a Task as done.
 */
public class DoneCommand implements Command {
    private int taskNumber;

    /**
     * Creates a DoneCommand.
     *
     * @param taskNumber the number (1-based) of the Task to mark as done.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Ui ui, TaskList list) {
        try {
            Helper.validateTaskNumber(taskNumber, list);
        } catch (InvalidTaskNumberException e) {
            ui.say(e.getMessage());
            return;
        }

        StringBuilder sb = new StringBuilder();
        Task t = list.get(taskNumber - 1);
        if (t.isDone()) {
            sb.append("You've already completed this task:\n");
        } else {
            list.markAsDone(taskNumber - 1);
            sb.append("Nice! I've marked this task as done:\n");
        }
        sb.append("  " + t.displayString());
        ui.say(sb.toString());
    }
}
