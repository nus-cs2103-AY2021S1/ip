package dukechatbot.parser;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CommandParserTest {

    @Test
    void parse_invalid_command() {
        String invalidCommand = "lol123";
        assertNull(CommandParser.parse(invalidCommand));
    }
}
