package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public interface Command {
    String execute(TaskList tasks) throws DukeException;

    default boolean setIsFinished() {
        return false;
    }
}
