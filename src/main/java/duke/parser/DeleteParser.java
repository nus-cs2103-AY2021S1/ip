package duke.parser;

import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.exceptions.EmptyTaskException;

import java.time.format.DateTimeParseException;

public class DeleteParser extends Parser {
    public static Command parseDeleteCommand(String[] parseArray) throws EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException();
        } else {
            int arrayIndex = getArrayIndex(parseArray);
            return new DeleteCommand(arrayIndex);
        }
    }

}
