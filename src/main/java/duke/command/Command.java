package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;

/**
 * Abstract class for representing user commands.
 */
public abstract class Command {

    /**
     * Signals if command should terminate function loop.
     */
    protected boolean isByeCommand;

    /**
     * Creates a default <code>Command</code> that is not a <code>ByeCommand</code>.
     */
    protected Command() {
        this.isByeCommand = false;
    }

    /**
     * Executes the steps necessary for the command.
     *
     * @param ui Print-out and display manager.
     * @param taskManager <code>Task</code> manipulation manager.
     * @param saveManager Handles saving and loading.
     */
    public abstract void execute(Ui ui, TaskManager taskManager, SaveManager saveManager);

    /**
     * Signals if this command should terminate the software loop.
     *
     * @return Whether command is a <code>ByeCommand</code>
     */
    public boolean isByeCommand() {
        return this.isByeCommand;
    };

    /**
     * Returns a String representation of the class which includes the class name.
     *
     * @return Formatted String representation.
     */
    public String toString() {
        return this.getClass().getName();
    }

}
