package duke.parser;

import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.exceptions.EmptyTaskException;

public class DeleteParser extends Parser {

    /**
     * Returns a deleteCommands from an array of info extracted from user input.
     *
     * @param parseArray An array of info: ["delete", index].
     * @return A deleteCommand.
     * @throws EmptyTaskException If no index is specified.
     */
    public static Command parseDeleteCommand(String[] parseArray) throws EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException();
        } else {
            int arrayIndex = getArrayIndex(parseArray);
            return new DeleteCommand(arrayIndex);
        }
    }

}
