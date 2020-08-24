package duke.command;

import duke.Bot;
import duke.task.TaskList;

public class ListCommand implements Command {
    @Override
    public void execute(Bot bot, TaskList list) {
        if (list.size() == 0) {
            bot.sayLine("There are no items in your list.");
        } else {
            bot.sayLine("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                bot.sayLine((i + 1) + ". " + list.get(i).displayString());
            }
        }
    }
}
