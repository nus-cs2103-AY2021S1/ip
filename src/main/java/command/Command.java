package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Interface which would be implemented in Parser class when specific commands are called.
 */

public interface Command {
	String execute(TaskList taskList, Ui ui, Storage storage);
	boolean isExit();
}
