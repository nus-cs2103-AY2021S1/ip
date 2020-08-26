package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;

public interface CommandExecutor {
    public String execute(String in, TaskList taskList, Storage storage) throws DukeException;

    public boolean shouldExit();
}
