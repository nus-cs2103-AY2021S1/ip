package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {

    private Parser parser = new Parser();

    @Test
    void parse_invalid_command() {
        String invalidCommand = "lol123";
        assertNull(this.parser.parse(invalidCommand));
    }
}
