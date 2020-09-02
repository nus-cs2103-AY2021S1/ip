package duke;

/**
 * Interface which would be implemented in Parser class when specific commands are called.
 */

public interface Command {
	String execute(TaskList taskList, Ui ui, Storage storage);
	boolean isExit();
}
