/**
 * A Command is a parsed request from the user.
 */
public abstract class Command {

	/**
	 * Modifies tasks, ui and storage of the Duke instance depending on the type of Command.
	 *
	 * @param tasks TaskList to be modified.
	 * @param ui Ui to be used to display feedback messages.
	 * @param storage Storage to be activated if there are any changes to TaskList.
	 * @throws DukeException
	 */
	public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

	/**
	 * Returns false for all commands except ExitCommand.
	 *
	 * @return Whether Command is an ExitCommand.
	 */
	public boolean isExit() {
		return false;
	}
}
