package duke.parser;

import duke.commands.Command;
import duke.commands.DoneCommand;
import duke.exceptions.EmptyTaskException;

public class DoneParser extends Parser {

    /**
     * Returns a doneCommands from an array of info extracted from user input.
     *
     * @param parseArray An array of info: ["done", index].
     * @return A doneCommand.
     * @throws EmptyTaskException If no index is specified.
     */
    public static Command parseDoneCommand(String[] parseArray) throws EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException();
        } else {
            int arrayIndex = getArrayIndex(parseArray);
            return new DoneCommand(arrayIndex);
        }
    }
}
