package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

	static String EXIT_MESSAGE = "Goodbye! Hope to see you again soon!";

	/**
	 * Prints exit message on the Ui.
	 *
	 * @param taskList The TaskList used by Duke.
	 * @param ui The Ui used by Duke.
	 * @param storage The Storage used by Duke.
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) {
		ui.printMessage(EXIT_MESSAGE);
	}

	/**
	 * Returns true since this is a ExitCommand.
	 *
	 * @return true.
	 */
	@Override
	public boolean isExitCommand() {
		return true;
	}
}
