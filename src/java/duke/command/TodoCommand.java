package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeExecutionException;
import duke.task.Todo;

import java.io.IOException;

public class TodoCommand extends Command {

	Todo todo;

	public TodoCommand(String name) {
		this.todo = new Todo(name);
	}

	@Override
	public boolean isExit() {
		return super.isExit();
	}

	@Override
	public void execute(Storage storage) throws DukeExecutionException {
		try {
			storage.add(todo);
			Ui.showTaskAddition(todo);
		} catch (IOException e) {
			throw new DukeExecutionException("Could not execute command due to IO exception.");
		}
	}
}
