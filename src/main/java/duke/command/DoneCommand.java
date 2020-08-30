package duke.command;

import duke.Bot;
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
     * @param taskNumber the number (1-based) of the Task to mark as done
     */
    public DoneCommand(int taskNumber) {
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
        Task t = list.get(taskNumber - 1);
        if (t.isDone()) {
            bot.sayLine("You've already completed this task:");
        } else {
            list.markAsDone(taskNumber);
            bot.sayLine("Nice! I've marked this task as done:");
        }
        bot.sayLine("  " + t.displayString());
    }
}
