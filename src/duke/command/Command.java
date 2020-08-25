package duke.command;

import duke.storage.Storage;
import duke.exception.DukeExecutionException;

public abstract class Command {

	public boolean isExit() {
		return false;
	}

	public abstract void execute(Storage storage) throws DukeExecutionException;

}
