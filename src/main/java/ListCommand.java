import java.util.Date;

public class ListCommand extends Command {

	private final Date on;

	public ListCommand() {
		this.on = null;
	}

	public ListCommand(Date on) {
		this.on = on;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.formatList(tasks, on);
	}
}
