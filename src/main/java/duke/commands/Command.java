package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the Command object to handle logic of user inputs.
 * Abstract class to be implemented by AddCommand, ByeCommand, DeleteCommand,
 * DoneCommand and ListCommand.
 * Creates appropriate command to handle the logic of the bot.
 */
public abstract class Command {
    protected boolean isExit;
    private int itemIndex;

    public Command() {
        this.isExit = false;
        this.itemIndex = -1;
    }

    /**
     * Returns boolean value signalling if the command should terminate the bot.
     *
     * @return isExit property of the command.
     */
    public boolean canExit() {
        return this.isExit;
    }

    public abstract void execute(Ui ui, Storage listStorage, TaskList taskList);
}
