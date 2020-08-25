package duke.command;

import duke.storage.Storage;

public class ExitCommand extends Command {

	@Override
	public void execute(Storage storage) {
		// Do nothing.
	}

	@Override
	public boolean isExit() {
		return true;
	}
}
