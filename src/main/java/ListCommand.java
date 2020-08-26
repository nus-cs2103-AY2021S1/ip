import java.util.Date;

public class ListCommand extends Command {

	private final Date on;
	private final String keyWord;

	public ListCommand(Date on, String keyWord) {
		this.on = on;
		this.keyWord = keyWord;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.formatList(tasks, on, keyWord);
	}
}
