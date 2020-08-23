public class ExitCommand extends Command {

	static String EXIT_MESSAGE = "Goodbye! Hope to see you again soon!";

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) {
		ui.printMessage(EXIT_MESSAGE);
	}

	@Override
	public boolean isExitCommand() {
		return true;
	}
}
