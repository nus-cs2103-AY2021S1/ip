package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Command to exit duke
 *
 * This command only displays the exit statement while using GUI and does not exit the program.
 */

public class CommandBye implements Command{
	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
		String string = "Bye. Hope to see you again soon!";
		return string;
	}

	@Override
	public boolean isExit() {
		return true;
	}
}
