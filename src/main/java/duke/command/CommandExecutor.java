package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public interface CommandExecutor {
    public String execute(String in, TaskList taskList) throws DukeException;

    public boolean shouldExit();
}
