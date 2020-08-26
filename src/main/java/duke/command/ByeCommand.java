package duke.command;

import duke.Bot;
import duke.task.TaskList;

/**
 * This Command will cause Duke to exit.
 */
public class ByeCommand implements Command {
    @Override
    public void execute(Bot bot, TaskList list) {
        bot.sayLine("Bye! Hope to see you again soon!");
        bot.stop();
    }
}
