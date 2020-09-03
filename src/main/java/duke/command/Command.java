package duke.command;

import duke.TaskList;

public interface Command {
    void execute(TaskList tasks);

    default boolean setIsFinished() {
        return false;
    }
}
