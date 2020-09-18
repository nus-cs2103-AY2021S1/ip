package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

/**
 * Command deals with all commands recognisable by Duke.
 */
public abstract class Command {

    public abstract boolean isExited();

    public abstract String execute(TaskList taskList, Storage storage) throws DukeException, IOException;
}
