package seedu.duke;

import seedu.duke.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ParserTest {
    @Test
    void listCommand() {
        assertDoesNotThrow(() -> Parser.parse("list"));
    }
}
