package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList taskList) throws DukeException;
}
