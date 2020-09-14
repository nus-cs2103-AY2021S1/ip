package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Save Command
 *
 * Saves the TaskList to the data file.
 * Path name of the file is specified in duke's static variable PATH_NAME.
 */
public class CommandSave implements Command {
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		String temp = storage.save(taskList);
		return temp;
	}

	@Override
	public boolean isExit() {
		return false;
	}
}
