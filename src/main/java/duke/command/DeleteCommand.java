package duke.command;

import duke.Bot;
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
     * @param taskNumber the number (1-based) of the Task to delete
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Bot bot, TaskList list) {
        try {
            Helper.validateTaskNumber(taskNumber, list);
        } catch (InvalidTaskNumberException e) {
            bot.sayLine(e.getMessage());
            return;
        }
        Task t = list.delete(taskNumber - 1);
        bot.sayLine("Noted. I've removed this task:");
        bot.sayLine("  " + t.displayString());
        bot.sayLine(Helper.getNumberOfTasksString(list));
    }
}
