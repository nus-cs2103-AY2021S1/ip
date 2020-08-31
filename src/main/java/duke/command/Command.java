package duke.command;

import duke.dukeexception.DukeException;
import duke.Storage;
import duke.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
