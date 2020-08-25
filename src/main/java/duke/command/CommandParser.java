package duke.command;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

public class CommandParser {

    protected static final String INDICATOR_CLOSING = "bye";
    protected static final String INDICATOR_LIST = "list";
    protected static final String INDICATOR_DONE = "done";
    protected static final String INDICATOR_DELETE = "delete";

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

