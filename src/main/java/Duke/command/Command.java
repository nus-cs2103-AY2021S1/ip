package Duke.command;

import Duke.exception.DukeException;
import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.Ui;

/**
 * Represents a user command. Consists of an execute method where the command manipulates
 * the TaskList object, Ui object and Storage object to achieve the desired outcome. Each
 * Command will extend from this abstract class and have their own execute and isExit method.
 * */
public abstract class Command {
	public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

	public abstract boolean isExit();
}
