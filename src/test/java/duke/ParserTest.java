package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class that encapsulates tests for the Parser class.
 */
public class ParserTest {
    /**
     * Tests if the input "bye" would return an ExitCommand.
     * @throws DukeException if DukeException is thrown in Parser.parse(...).
     */
    @Test
    public void parse_bye_exitTrue() throws DukeException {
        assertEquals(new ExitCommand().isExit(), Parser.parse("bye").isExit());
    }
}
