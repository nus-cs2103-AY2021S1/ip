package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {

	/**
	 * Prints out the entire list in the storage
	 *
	 * @param storage The duke.storage.Storage object from which to print the list
	 */
	@Override
	public void execute(Storage storage) {
		Ui.wrapText(storage.toString());
	}

}
