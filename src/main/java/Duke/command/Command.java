package Duke.command;

import Duke.exception.DukeException;
import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.Ui;

public abstract class Command {
	public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

	public abstract boolean isExit();
}
