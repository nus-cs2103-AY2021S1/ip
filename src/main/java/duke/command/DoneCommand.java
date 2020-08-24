package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
	int indexOfTask;

	public DoneCommand(int indexOfTask) {
		this.indexOfTask = indexOfTask;
	}
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
		Task taskToSetDone = taskList.getTask(indexOfTask);
		taskList.setTaskDone(indexOfTask);
		storage.updateStorage(taskList);
		ui.printMessage("Nice! I've marked this task as done:\n" +
				taskToSetDone.toString());
	}
}
