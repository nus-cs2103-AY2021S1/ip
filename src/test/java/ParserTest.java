import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Parser;
import duke.exception.DukeException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parser_invalidInput_throwsException() {
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("invalid input");
        });
        String expected = "Oh no! Invalid Function! Input '/commands' for a list of all my commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void parser_missingUserInput_throwsException() {
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse(" ");
        });
        String expected = "Oh no! No input was entered! Please enter something!";
        assertEquals(expected, ex.getMessage());
    }
}