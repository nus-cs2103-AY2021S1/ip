package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    public abstract boolean isExit();

    public abstract void execute(String input, TaskList taskList, Storage storage)
            throws DukeException;
}
