package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class to give the standard behaviors of the commands.
 */
import exceptions.DukeException;

public abstract class Command {
    protected String fullCommand;

    Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public abstract boolean isExit();

    public abstract void executeCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
