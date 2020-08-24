package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

public abstract class Command {

	public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

	protected String convertTaskListToSaveFormat(TaskList taskList) throws DukeException {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < taskList.numberOfTasks(); i++) {
			Task task = taskList.getTask(i);
			String saveTaskString = convertTaskToSaveFormat(task);
			stringBuilder.append(saveTaskString + "\n");
		}

		return stringBuilder.toString();
	}

	String convertTaskToSaveFormat(Task task) throws DukeException {
		String taskType;
		String description = task.getDescription();
		int taskDone = task.isDone() ? 1 : 0;
		String resultString;
		if (task instanceof ToDo) {
			taskType = "T";
			resultString = taskType + " | " + taskDone + " | " + description;
		} else if (task instanceof Deadline) {
			Deadline deadline = (Deadline) task;
			taskType = "D";
			String date = deadline.getLocalDate().toString();
			String time = deadline.getLocalTime().toString();
			resultString = taskType + " | " + taskDone + " | " + description + " | " + date + " | " + time;
		} else if (task instanceof Event) {
			Event event = (Event) task;
			taskType = "E";
			String date = event.getLocalDate().toString();
			String time = event.getLocalTime().toString();
			resultString = taskType + " | " + taskDone + " | " + description + " | " + date + " | " + time;
		} else {
			throw new DukeException("Unable to save task, unknown task type");
		}

		return resultString;
	}

	public boolean isExitCommand() {
		return false;
	}
}
