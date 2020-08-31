package duke;

import duke.TaskList;
import duke.Ui;
import duke.Storage;


public interface Command {
	public void execute(TaskList taskList, Ui ui, Storage storage);
	public boolean isExit();
}