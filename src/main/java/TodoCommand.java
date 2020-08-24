import java.io.IOException;

public class TodoCommand extends Command {

	Todo todo;

	TodoCommand(String name) {
		this.todo = new Todo(name);
	}

	@Override
	public boolean isExit() {
		return super.isExit();
	}

	@Override
	void execute(Storage storage) throws DukeExecutionException {
		try {
			storage.add(todo);
			Ui.showTaskAddition(todo);
		} catch (IOException e) {
			throw new DukeExecutionException("Could not execute command due to IO exception.");
		}
	}
}
