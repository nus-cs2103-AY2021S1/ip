/**
 * DeleteCommand is a request to stop Duke from running.
 */

public class ExitCommand extends Command {

	/**
	 * No action is required from ExitCommand.
	 *
	 * @param tasks TaskList to be modified.
	 * @param ui Ui to be used to display feedback messages.
	 * @param storage Storage to be activated if there are any changes to TaskList.
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
	}

	@Override
	public boolean isExit() {
		return true;
	}
}
