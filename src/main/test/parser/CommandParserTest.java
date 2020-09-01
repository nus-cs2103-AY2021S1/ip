package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandParserTest {

    @Test
    void parse_invalid_command() {
        String invalidCommand = "lol123";
        assertNull(CommandParser.parse(invalidCommand));
    }
}
