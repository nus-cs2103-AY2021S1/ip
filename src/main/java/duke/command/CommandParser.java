package duke.command;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

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

    /**
     * Returns the appropriate command type based on the string input.
     * The method uses the first word to determine that type of command to create.
     * @param userInput string containing the command to be created
     * @return command object corresponding to the details provided
     */
    public static Command parse(String userInput) {
        String firstWord = userInput.split(" ")[0];
        if (userInput.equals(INDICATOR_CLOSING)) {
            return ExitCommand.create();
        } else if (userInput.equals(INDICATOR_LIST)) {
            return ListCommand.create();
        } else if (firstWord.equals(INDICATOR_DONE)) {
            return DoneCommand.parse(userInput);
        } else if (firstWord.equals(INDICATOR_DELETE)) {
            return DeleteCommand.parse(userInput);
        } else {
            return AddCommand.parse(userInput);
        }

    }



}

