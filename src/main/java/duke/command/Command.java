package duke.command;

import duke.component.*;

/**
 * Interface for all command classes
 */
public interface Command {
    public boolean isExit();
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
