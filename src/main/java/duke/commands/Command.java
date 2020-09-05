package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidCommand;

/**
 * Represents the Command object to handle logic of user inputs.
 * Abstract class to be implemented by AddCommand, ByeCommand, DeleteCommand,
 * DoneCommand and ListCommand.
 * Creates appropriate command to handle the logic of the bot.
 */
public abstract class Command {
    protected boolean isExit;
    private int itemIndex;

    /**
     * Creates a Command object to handle logic of user's input.
     */
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

    /**
     * Executes main logic of command object. To be implemented by subclasses.
     *
     * @param ui
     * @param listStorage
     * @param taskList
     * @return UI message representing the relevant execution.
     */
    public abstract String execute(Ui ui, Storage listStorage, TaskList taskList) throws InvalidCommand;
}
