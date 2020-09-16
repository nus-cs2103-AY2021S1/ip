package duke.command;

import duke.exception.DukeException;
import duke.logic.Tasklist;
import duke.logic.Storage;

/**
 * Represents a Command that can be executed by Duke.
 */
public abstract class Command {

    public Command() {}

    public abstract String execute(Tasklist tasks, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
