package duke.command;

import duke.Bot;
import duke.task.TaskList;

public interface Command {
    public void execute(Bot bot, TaskList list);
}
