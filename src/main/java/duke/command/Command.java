package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command. Consists of an execute method where the command manipulates
 * the TaskList object, Ui object and Storage object to achieve the desired outcome. Each
 * Command will extend from this abstract class and have their own execute and isExit method.
 * */
public abstract class Command {
	public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

	public abstract boolean isExit();
}
