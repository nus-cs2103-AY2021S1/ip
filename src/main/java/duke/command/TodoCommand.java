package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {

	private String description;

	public TodoCommand(String tsk) {
		this.description = tsk;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		Task todo = new Todo(this.description);
		tasks.add(todo);

		//print output
		ui.printTaskAdded(tasks, todo);

		//update storage
		storage.saveListToHardDisk(tasks);
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
