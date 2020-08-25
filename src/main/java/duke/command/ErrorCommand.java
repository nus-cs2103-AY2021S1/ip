package duke.command;

import duke.Bot;
import duke.task.TaskList;

public class ErrorCommand implements Command {
    private String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(Bot bot, TaskList list) {
        bot.sayLine(message);
    }
}
