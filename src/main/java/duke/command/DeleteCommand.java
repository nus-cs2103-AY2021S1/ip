package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * This Command will delete a Task from the TaskList.
 */
public class DeleteCommand implements Command {
    private int taskNumber;

    /**
     * Creates a DeleteCommand.
     *
     * @param taskNumber the number (1-based) of the Task to delete.
     */
    public DeleteCommand(int taskNumber) {
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
        Task t = list.delete(taskNumber - 1);
        ui.say(String.format("Noted. I've removed this task:\n  %s\n%s", t.displayString(),
                Helper.getNumberOfTasksString(list)));
    }
}
