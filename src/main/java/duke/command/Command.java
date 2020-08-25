package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

public abstract class Command {
	/**
	 * A method to execute the command.
	 *
	 * @param tasks The TaskList used by Duke.
	 * @param ui The Ui used by Duke.
	 * @param storage The Storage used by Duke.
	 * @throws DukeException If a DukeException occurs during execution by child classes.
	 */
	public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;


	/**
	 * Returns whether the command object is the ExitCommand.
	 * @return A boolean of whether the command is an ExitCommand and default set to false.
	 */
	public boolean isExitCommand() {
		return false;
	}
}
