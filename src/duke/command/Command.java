package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    /**
     * Executes the command on the current program's tasks, UI and stored file based on type of command and user input.
     *
     * @param tasks the tasklist to modify during execution.
     * @param ui the frontend interface to communicate with the user.
     * @param storage the storage file to save and load tasks from.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public Command() { };

    /**
     * Returns whether the command should cause the program to exit.
     *
     * @return boolean value of whether the command should cause the program to exit.
     */
    public boolean isExit() {
        return false;
    }
}