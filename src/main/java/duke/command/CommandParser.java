package duke.command;

import duke.DukeException;

/**
 * Class handles the parsing of Strings into the appropriate type of command objects.
 */
public class CommandParser {

    /** exit-type string indicator */
    protected static final String INDICATOR_CLOSING = "bye";
    /** list-type string indicator */
    protected static final String INDICATOR_LIST = "list";
    /** done-type string indicator */
    protected static final String INDICATOR_DONE = "done";
    /** delete-type string indicator */
    protected static final String INDICATOR_DELETE = "delete";
    protected static final String INDICATOR_FIND = "find";

    /**
     * Returns the appropriate command type based on the string input.
     * The method uses the first word to determine that type of command to create.
     * @param userInput string containing the command to be created
     * @return command object corresponding to the details provided
     * @throws DukeException if the user input given is invalid, with the reason provided
     */
    public static Command parse(String userInput) throws DukeException {
        String firstWord = userInput.split(" ")[0];
        switch (firstWord) {
        case INDICATOR_CLOSING:
            return ExitCommand.create();
        case INDICATOR_LIST:
            return ListCommand.create();
        case INDICATOR_DONE:
            return DoneCommand.parse(userInput);
        case INDICATOR_DELETE:
            return DeleteCommand.parse(userInput);
        case INDICATOR_FIND:
            return FindCommand.parse(userInput);
        default:
            return AddCommand.parse(userInput);
        }
    }



}

