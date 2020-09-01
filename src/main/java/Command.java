package duke;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Interface which would be implemented in Parser class when specific commands are called.
 */

public interface Command {
	public String execute(TaskList taskList, Ui ui, Storage storage);
	public boolean isExit();
}