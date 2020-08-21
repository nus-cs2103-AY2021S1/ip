package duke.command;

import duke.Bot;
import duke.task.Task;

import java.util.Arrays;
import java.util.List;

public class ErrorCommand implements Command {
    private List<String> message;

    public ErrorCommand(List<String> message) {
        this.message = message;
    }

    public ErrorCommand(String message) {
        this(Arrays.asList(message));
    }

    @Override
    public void execute(Bot bot, List<Task> list) {
        bot.sayLines(message);
    }
}
