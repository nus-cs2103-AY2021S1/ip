import java.util.Date;

/**
 * ListCommand is a request to display all current tasks.
 */

public class ListCommand extends Command {

	private final Date on;
	private final String keyWord;

	public ListCommand(Date on, String keyWord) {
		this.on = on;
		this.keyWord = keyWord;
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
		ui.showTaskList(tasks, on, keyWord);
	}
}
