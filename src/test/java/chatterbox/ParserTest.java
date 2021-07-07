package chatterbox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseDateTime() {
        assertNull(Parser.parseDateTimeFromString("0293a"));
        assertEquals(Parser.parseDateTimeFromString("1/12/2020 1800"),
                LocalDateTime.of(2020, 12, 1, 18, 0, 0, 0));
        assertEquals(Parser.parseDateTimeFromString("12-6-2020 2330"),
                LocalDateTime.of(2020, 6, 12, 23, 30, 0, 0));
        assertEquals(Parser.parseDateTimeFromString("1/1/2012"),
                LocalDateTime.of(2012, 1, 1, 0, 0, 0, 0));
        assertEquals(Parser.parseDateTimeFromString("24-8-1900"),
                LocalDateTime.of(1900, 8, 24, 0, 0, 0, 0));
    }

    @Test
    public void parse_emptyCommandDescription_exceptionThrown() {
        ChatterboxException e = assertThrows(ChatterboxException.class, () -> Parser.parseTask("todo"));
        assertEquals("The description of a todo cannot be empty", e.getMessage());
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        ChatterboxException e = assertThrows(ChatterboxException.class, () -> Parser.parseTask("todos check"));
        assertEquals("Invalid command.", e.getMessage());
    }
}
