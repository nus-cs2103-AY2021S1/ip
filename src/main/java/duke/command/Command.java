package duke.command;

import duke.*;
import duke.exception.*;

/**
 * Represents an action to be taken depending on the <code>tag</code> and <code>input</code>.
 */
public abstract class Command {
    protected String tag;
    protected String input;
    protected boolean isExit;

    /**
     * Class constructor with no additional inputs necessary.
     *
     * @param tag A string representing the tag of the command.
     */
    public Command(String tag) {
        this.tag = tag;
        this.input = "";
        this.isExit = false;
    }

    /**
     * Class constructor with the additional user input.
     *
     * @param tag   A string representing the tag of the command.
     * @param input A string representing the user input.
     */
    public Command(String tag, String input) {
        this.tag = tag;
        this.input = input;
        this.isExit = false;
    }

    /**
     * Class constructor for a command to exit the chat bot.
     *
     * @param tag    A string representing the tag of the command.
     * @param isExit <code>true</code> if the command signals exiting the chat bot;
     *               <code>false</code> otherwise.
     */
    public Command(String tag, boolean isExit) {
        this.tag = tag;
        this.input = "";
        this.isExit = isExit;
    }

    /**
     * Signals whether or not to exit the chat bot.
     *
     * @return <code>true</code> if the command signals exiting the chat bot;
     *         <code>false</code> otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Performs the appropriate command depending on its type.
     *
     * @param tasks   The list of tasks.
     * @param ui      The platform through which both user and chat bot interact.
     * @param storage The object responsible for saving and loading data.
     * @throws DukeInvalidListNumberInputException If an invalid list number is given.
     * @throws DukeInvalidTaskTimeException        If an invalid task time is given.
     * @throws DukeInvalidTaskDescriptionException If an invalid task description is given.
     * @throws DukeUnknownInputException           If the user input cannot be understood.
     * @throws DukeLoadingErrorException           If the file containing the save data cannot be loaded.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeInvalidListNumberInputException,
            DukeInvalidTaskTimeException,
            DukeInvalidTaskDescriptionException,
            DukeUnknownInputException,
            DukeLoadingErrorException;
}
