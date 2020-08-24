package chatterbox;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testParseDateTime() {
        assertNull(Parser.parseDateTime("0293a"));
        assertEquals(Parser.parseDateTime("1/12/2020 1800"),
                LocalDateTime.of(2020, 12, 1, 18, 0, 0, 0));
        assertEquals(Parser.parseDateTime("12-6-2020 2330"),
                LocalDateTime.of(2020, 6, 12, 23, 30, 0, 0));
        assertEquals(Parser.parseDateTime("1/1/2012"),
                LocalDateTime.of(2012, 1, 1, 0, 0, 0, 0));
        assertEquals(Parser.parseDateTime("24-8-1900"),
                LocalDateTime.of(1900, 8, 24, 0, 0, 0, 0));
    }

    @Test
    public void parse_emptyCommandDescription_exceptionThrown() {
        try {
            Parser.parseTask("todo");
            fail();
        } catch (ChatterboxException e) {
            assertEquals("The description of a todo cannot be empty", e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Parser.parseTask("todos check");
            fail();
        } catch (ChatterboxException e) {
            assertEquals("Invalid command.", e.getMessage());
        }
    }
}
