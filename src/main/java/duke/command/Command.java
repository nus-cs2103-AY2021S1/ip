package duke.command;

import duke.util.DukeException;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * The command is the response of the program to be executed.
 * Execution of command may involve manipulation of TaskList,
 * Ui, or Storage depending on the required response. Implementations
 * of Command will mainly function using the execute method.
 */
public interface Command {
    /**
     * Identifies if the command calls for an exit of the program.
     * @return the value of whether the command is an exit command.
     */
    boolean isExit();

    /**
     * Executes the command and generates the response message.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     * @return the response message.
     * @throws DukeException if command execution fails.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
