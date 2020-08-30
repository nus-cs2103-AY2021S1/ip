package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * The Command is the response of the program to be executed.
 * Execution of command may involve manipulation of TaskList,
 * Ui, or Storage depending on the required response.
 */
public interface Command {
    boolean isExit();
    String execute(TaskList tasks, Ui ui, Storage storage);
}
