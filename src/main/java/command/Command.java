package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Interface for Commands
 * Implementation have to overwrite execute and isExit methods.
 */

public interface Command {
	String execute(TaskList taskList, Ui ui, Storage storage);
	boolean isExit();
}
