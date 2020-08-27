import java.util.Date;

/**
 * ListCommand is a request to display all current tasks.
 */

public class ListCommand extends Command {

	private final Date on;

	public ListCommand() {
		this.on = null;
	}

	public ListCommand(Date on) {
		this.on = on;
	}

	/**
	 * Displays all current tasks with their TaskType, done status and description.
	 *
	 * @param tasks TaskList to be printed.
	 * @param ui Ui to be used to display feedback messages.
	 * @param storage Storage is not activated.
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.showTaskList(tasks, on);
	}
}
