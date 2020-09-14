package duke.command;

import duke.DukeException;

/**
 * A class of exceptions arising from unaccepted user inputs.
 */
public class UserInputException extends DukeException {

    /** An exception thrown when user input is not recognized by the parser. */
    public static final UserInputException INVALID_COMMAND = new UserInputException(
            "I'm sorry, but I don't know what that means :-(");

    /** An exception thrown when user inputs an empty search query. */
    public static final UserInputException EMPTY_QUERY = new UserInputException(
            "I'm sorry, what was it you wanted me to find?");

    /**
     * An exception thrown when user inputs an empty or wrongly formatted task index.
     */
    public static final UserInputException INVALID_TASK_INDEX = new UserInputException(
            "Sorry, that is not a valid task.");

    /** An exception thrown when user inputs an empty task. */
    public static final UserInputException INVALID_TASK_DESCRIPTION = new UserInputException(
            "Sorry, the description of a task cannot be empty.");

    /** An exception thrown when user inputs a deadline with an incorrect format. */
    public static final UserInputException INVALID_DEADLINE_FORMAT = new UserInputException(
            "Sorry, the description of a Deadline must be in this format:\n"
                    + "    deadline [task name] /by [deadline]");

    /** An exception thrown when user inputs an event with an incorrect format. */
    public static final UserInputException INVALID_EVENT_FORMAT = new UserInputException(
            "Sorry, the description of an Event must be in this format:\n"
                    + "    event [task name] /at [time]");

    /** An exception thrown when user input is missing the category. */
    public static final UserInputException EMPTY_CATEGORY = new UserInputException(
            "Sorry, you did not enter a category.");

    /** An exception thrown when user input on the amount is missing, negative or improperly formatted. */
    public static final UserInputException INVALID_AMOUNT = new UserInputException(
            "Sorry, to add/reduce the amount you have to specify a positive double.\n"
                    + "    e.g. ADD Meals 4.50\n"
                    + "    or   REDUCE TRANSPORT 0.05.");

    /** An exception thrown when user tries to access a category that does not exist in the finance-list. */
    public static final UserInputException NO_SUCH_CATEGORY = new UserInputException(
            "Sorry, that category does not exist yet.");

    /** An exception thrown when user tries to add an entry that already exist in the list. */
    public static final UserInputException DUPLICATE_ENTRY = new UserInputException(
            "That entry already exists in the list.");

    /** Constructs a new UserInputException. */
    private UserInputException(String message) {
        super(message);
    }

}
