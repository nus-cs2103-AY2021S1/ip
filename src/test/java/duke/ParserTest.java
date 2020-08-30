package duke;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseListTest() throws DukeException {
        Command command = Parser.parse("list");
        assertNotNull(command);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseUnknownCommandTest() {
        assertThrows(DukeException.class, ()->Parser.parse("lol"));
    }
}
