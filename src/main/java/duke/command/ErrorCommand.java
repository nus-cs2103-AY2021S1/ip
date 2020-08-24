package duke.command;

import duke.Bot;
import duke.task.Task;

import java.util.List;

public class ErrorCommand implements Command {
    private String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(Bot bot, List<Task> list) {
        bot.sayLine(message);
    }
}
