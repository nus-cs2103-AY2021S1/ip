package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Is responsible for handling commands
 */
public class Command {
    /**
     * Created when the command does not belong to any other types of command.
     */
    public Command() {}

    /**
     * Returns whether this command terminates the program.
     * @return An indicator that helps the program know when to stop (default is keep running)
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     * @param tasks A list of tasks
     * @param ui An Ui object that correspond to interacting with the user
     * @param storage A database that stores the task list locally when the program is not running
     * @throws DukeException When facing errors parsing or running the program
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showUnrecognizedCommandMessage();
    }
}
