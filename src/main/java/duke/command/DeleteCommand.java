package duke.command;

import duke.Bot;
import duke.task.Task;

import java.util.List;

public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Bot bot, List<Task> list) {
        if (list.isEmpty()) {
            bot.sayLine("Nothing to delete, your list is empty.");
        } else if (index < 0) {
            bot.sayLine("That's not a valid number, please give a number from 1 to " + list.size() + ".");
        } else if (index >= list.size()) {
            bot.sayLine(String.format("That's not a valid number, you only have %d item(s) in your list.", list.size()));
        } else {
            Task t = list.remove(index);
            bot.sayLine("Noted. I've removed this task:");
            bot.sayLine("  " + t.displayString());
            bot.sayLine("Now you have " + list.size() + " item(s) in your list.");
        }
    }
}
