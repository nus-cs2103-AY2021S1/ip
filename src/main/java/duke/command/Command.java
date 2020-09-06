package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Interface for all command classes
 */
public interface Command {
    boolean isExit();

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
