package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
            ui.say(e.getMessage(), true);
            return;
        }

        StringBuilder sb = new StringBuilder();
        Task t = list.get(taskNumber - 1);

        if (t.isDone()) {
            sb.append("You've already completed this task:\n");
        } else {
            t.markAsDone();
            sb.append("Nice! I've marked this task as done:\n");
        }
        sb.append("  " + t.displayString());
        assert !sb.toString().isBlank();
        ui.say(sb.toString());
    }
}
