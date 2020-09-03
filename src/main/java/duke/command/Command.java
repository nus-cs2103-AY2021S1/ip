package duke.command;

import duke.TaskList;

public interface Command {
    String execute(TaskList tasks);

    default boolean setIsFinished() {
        return false;
    }
}
