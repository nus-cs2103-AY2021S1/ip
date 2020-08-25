package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parse_list_ListCommand() throws DukeException {
        Command command = Parser.parse("list");
        assertNotNull(command);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_unknownCommand_DukeException() {
        assertThrows(DukeException.class, ()->Parser.parse("lol"));
    }
}
