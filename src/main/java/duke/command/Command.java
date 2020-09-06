package duke.command;

import java.util.LinkedList;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Interface for all command classes.
 */
public interface Command {
    boolean isExit();

    String execute(TaskList taskList, Ui ui, Storage storage,
                   LinkedList<ReversibleCommand> reversibleCommands) throws DukeException;
}
