package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;

public abstract class Command {

    public abstract void execute(TaskListHandler handler, Storage storage) throws DukeException;

}
