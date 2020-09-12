package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import duke.Parser;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    void listCommand() {
        assertDoesNotThrow(() -> Parser.parse("list"));
    }
}
