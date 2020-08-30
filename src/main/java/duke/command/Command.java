package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.exception.DukeInvalidKeywordException;
import duke.exception.DukeInvalidListNumberInputException;
import duke.exception.DukeInvalidTaskDescriptionException;
import duke.exception.DukeInvalidTaskTimeException;
import duke.exception.DukeLoadingErrorException;
import duke.exception.DukeUnknownInputException;

/**
 * Represents an action to be taken depending on the <code>tag</code> and <code>input</code>.
 */
public abstract class Command {

    protected String tag;
    protected String input;

    /**
     * Class constructor.
     *
     * @param tag   A string representing the tag of the command.
     * @param input A string representing the user input.
     */
    protected Command(String tag, String input) {
        this.tag = tag;
        this.input = input;
    }

    /**
     * Class constructor with no additional inputs necessary.
     *
     * @param tag A string representing the tag of the command.
     */
    protected Command(String tag) {
        this(tag, "");
    }

    /**
     * Performs the appropriate command depending on its type.
     *
     * @param tasks   The list of tasks.
     * @param ui      The platform through which both user and chat bot interact.
     * @param storage The object responsible for saving and loading data.
     * @return A command result with the appropriate response for the user.
     * @throws DukeInvalidListNumberInputException If an invalid list number is given.
     * @throws DukeInvalidTaskTimeException        If an invalid task time is given.
     * @throws DukeInvalidTaskDescriptionException If an invalid task description is given.
     * @throws DukeUnknownInputException           If the user input cannot be understood.
     * @throws DukeLoadingErrorException           If the file containing the save data cannot be loaded.
     * @throws DukeInvalidKeywordException         If the user input contains an invalid keyword.
     */
    public abstract CommandResult execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeInvalidListNumberInputException, DukeInvalidTaskTimeException,
            DukeInvalidTaskDescriptionException, DukeUnknownInputException,
            DukeLoadingErrorException, DukeInvalidKeywordException;

}
