package Duke.command;

import Duke.storage.Storage;
import Duke.task.Deadline;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.ui.Ui;

public class DeadlineCommand extends Command {

	private String description;
	private String by;

	public DeadlineCommand(String description, String by) {
		this.description = description;
		this.by = by;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		Task deadline = new Deadline(this.description, this.by);
		tasks.add(deadline);

		//print output
		ui.printTaskAdded(tasks, deadline);

		//update storage
		storage.saveListToHardDisk(tasks);
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
