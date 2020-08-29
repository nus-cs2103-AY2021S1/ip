package command;

import exceptions.DukeException;
import parserstorageui.Storage;
import parserstorageui.Ui;
import task.TaskList;


abstract public class Command {

    protected final String command;

    public Command(String command) {
        this.command = command;
    }

    /**
     * Executes the command
     **/
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Check if the current command is an exit command
     **/
    public abstract boolean isExit();

}
