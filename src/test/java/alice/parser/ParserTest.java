package alice.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import alice.command.InvalidCommandException;

public class ParserTest {
    @Test
    void parseDateTimeTest() throws InvalidCommandException {
        LocalDateTime dateTime = Parser.parseDateTime("12-Aug 1350");
        assertEquals(LocalDateTime.of(2020, 8, 12, 13, 50), dateTime);
    }
}
