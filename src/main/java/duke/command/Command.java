package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

public abstract class Command {

	public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;



	public boolean isExitCommand() {
		return false;
	}
}
