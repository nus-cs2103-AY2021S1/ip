package duke.command;

import duke.task.TaskList;

public interface Command {
    String execute(TaskList tasks);

    default boolean setIsFinished() {
        return false;
    }
}
