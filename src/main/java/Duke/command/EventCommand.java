package Duke.command;

import Duke.storage.Storage;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.ui.Ui;

public class EventCommand extends Command {


	private String description;
	private String at;

	public EventCommand(String description, String at) {
		this.description = description;
		this.at = at;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		Task event = new Event(this.description, this.at);
		tasks.add(event);

		//print output
		ui.printTaskAdded(tasks, event);

		//update storage
		storage.saveListToHardDisk(tasks);

	}

	@Override
	public boolean isExit() {
		return false;
	}
}
