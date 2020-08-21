package duke.command;

import duke.Bot;
import duke.task.Task;

import java.util.List;

public class ListCommand implements Command {
    @Override
    public void execute(Bot bot, List<Task> list) {
        if (list.isEmpty()) {
            bot.sayLine("There are no items in your list.");
        } else {
            bot.sayLine("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                bot.sayLine((i + 1) + ". " + list.get(i).displayString());
            }
        }
    }
}
