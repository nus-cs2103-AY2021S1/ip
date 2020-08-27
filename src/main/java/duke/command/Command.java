package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

/**
 * Abstract class that is inherited by all command classes.
 */
public abstract class Command {
    public abstract void execute(Storage storage, TaskList taskList, UI ui) throws DukeException, IOException;
}
