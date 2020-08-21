package duke.command;

import duke.Bot;
import duke.task.Task;

import java.util.List;

public class ByeCommand implements Command {
    @Override
    public void execute(Bot bot, List<Task> list) {
        bot.sayLine("Bye! Hope to see you again soon!");
        bot.stop();
    }
}
