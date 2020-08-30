package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parse_bye_exitTrue() throws DukeException {
        assertEquals(new ExitCommand().isExit(), Parser.parse("bye").isExit());
    }
}
