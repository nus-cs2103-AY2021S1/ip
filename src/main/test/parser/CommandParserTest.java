package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandParserTest {

    private CommandParser commandParser = new CommandParser();

    @Test
    void parse_invalid_command() {
        String invalidCommand = "lol123";
        assertNull(this.commandParser.parse(invalidCommand));
    }
}
