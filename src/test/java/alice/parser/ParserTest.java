package alice.parser;

import alice.command.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    void parseDateTimeTest() {
        try {
            LocalDateTime dateTime = Parser.parseDateTime("12-Aug 1350");
            assertEquals(LocalDateTime.of(2020, 8, 12, 13, 50), dateTime);
        } catch (InvalidCommandException ignored) {

        }
    }
}
