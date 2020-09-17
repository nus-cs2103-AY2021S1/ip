package botbot.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class ParserTest {
    @Test
    public void parseCommandId_success() {
        assertEquals(0, Parser.parseCommandId("1"));
        assertEquals(2, Parser.parseCommandId("3"));
        assertEquals(5, Parser.parseCommandId("6"));
    }

    @Test
    public void parseCommandId_exceptionThrown() {
        try {
            Parser.parseCommandId("test");
            fail();
        } catch (NumberFormatException e) {
            assertEquals("For input string: \"test\"", e.getMessage());
        }
    }

    @Test
    public void parseDateTime_success() {
        assertEquals(LocalDateTime.of(2020, 5, 2, 3, 14, 15, 926535898),
                Parser.parseDateTime("2-5-2020"));
        assertEquals(LocalDateTime.of(2021, 2, 6, 3, 14),
                Parser.parseDateTime("6-2-2021 0314"));
        assertEquals(LocalDateTime.of(2022, 2, 15, 23, 59),
                Parser.parseDateTime("15-2-2022 2359"));
    }
    
    @Test
    public void parseDateTime_exceptionThrown() {
        try {
            Parser.parseDateTime("2/5/2020");
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text '2/5/2020 031415926535898' could not be parsed at index 1", e.getMessage());
        }
    }
}
