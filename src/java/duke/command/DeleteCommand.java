package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeArgumentException;
import duke.exception.DukeExecutionException;
import duke.exception.DukeIOException;

public class DeleteCommand extends Command {

	int index;

	public DeleteCommand(String args) throws DukeArgumentException {
		try {
			int index = Integer.parseInt(args);
			this.index = index;
		} catch (NumberFormatException ne) {
			throw new DukeArgumentException("Invalid index");
		}
	}

	@Override
	public boolean isExit() {
		return super.isExit();
	}

	@Override
	public void execute(Storage storage) throws DukeExecutionException {
		try {
			Ui.showTaskDeletion(storage.delete(index));
		} catch (IndexOutOfBoundsException e) {
			throw new DukeExecutionException(String.format("Could not execute command due to the index %d being out " +
					"of range", index));
		} catch (DukeIOException die) {
			throw new DukeExecutionException("Could not execute command due to IO exception.");
		}

	}
}
