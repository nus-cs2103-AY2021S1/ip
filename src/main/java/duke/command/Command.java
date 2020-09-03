package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command based on the user input.
 */
public abstract class Command {

    /**
     * Executes the command with the specified task list, ui and storage.
     *
     * @param tasks the task list that stores tasks.
     * @param ui the ui that deals with user interaction.
     * @param storage the storage that saves the tasks.
     * @throws DukeException if there are issues while executing the command.
     */
    public abstract void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true if an exit command is executed or false otherwise.
     */
    public abstract boolean isExit();
}
