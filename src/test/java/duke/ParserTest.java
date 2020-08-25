package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void parse_bye_exitTrue() throws DukeException {
        assertEquals(new ExitCommand().isExit(), Parser.parse("bye").isExit());
    }
}
