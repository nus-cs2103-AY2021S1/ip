package duke.parser;

import duke.commands.Command;
import duke.commands.DoneCommand;
import duke.exceptions.EmptyTaskException;

import java.time.format.DateTimeParseException;

public class DoneParser extends Parser {
    public static Command parseDoneCommand(String[] parseArray) throws EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException();
        } else {
            int arrayIndex = getArrayIndex(parseArray);
            return new DoneCommand(arrayIndex);
        }
    }
}
