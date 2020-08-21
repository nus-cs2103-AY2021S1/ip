package duke.command;

import duke.Bot;
import duke.task.Task;

import java.util.List;

public interface Command {
    public void execute(Bot bot, List<Task> list);
}
