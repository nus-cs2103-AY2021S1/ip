package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_deadlineTask_correctCommand() throws InvalidDescriptionException, InvalidCommandException,
            InvalidEventException, InvalidDeadlineException, InvalidDateFormatException {
        Command command = Parser.parse("deadline dsaasd /by 2016-03-23");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_invalidDateFormat_exceptionThrown() {
        assertThrows(InvalidDateFormatException.class, ()-> {
            Parser.parse("event dasda /at 2034-23232-12");
        });
    }
}
