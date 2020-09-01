import duke.Parser;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_byeCommand() throws DukeException {
        Command expected = new ExitCommand();
        Command actual = Parser.parse("bye");
        assertEquals(expected, actual);
    }

    @Test
    public void parse_unsupportedWord_exceptionThrown() {
        Throwable exception = assertThrows(DukeException.class, () -> Parser.parse("dodobird"));

        assertEquals("Invalid, not an accepted task type.", exception.toString());
    }
}
