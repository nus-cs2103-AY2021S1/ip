package dude.command;

import dude.util.CommandException;
import dude.util.Storage;
import dude.util.TaskList;
import dude.util.Ui;

/**
 * The abstract task contains the action of the command and whether the command calls for an exit.
 */
public abstract class Command {
    protected boolean isExit;
    private String action;

    /**
     * Constructor for the Command class.
     *
     * @param action Takes in the action the command is to perform.
     */
    public Command(String action) {
        this.action = action;
        isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException;

    public boolean getExitStatus() {
        return isExit;
    }
}
