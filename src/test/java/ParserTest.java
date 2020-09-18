import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.CheckCommand;
import duke.command.Command;
import duke.parser.Parser;

public class ParserTest {

    @Test
    public void parse_validInput_success() throws Exception {
        Command test = Parser.parse("check date and time");
        assertEquals(true, test instanceof CheckCommand);
    }

    @Test
    public void parse_invalidInput_exceptionThrown() {
        try {
            Command test = Parser.parse("onlyOneWord");
            fail();
        } catch (Exception e) {
            assertEquals("Only one word detected. Remember to add whitespace "
                    + "between information or enter a valid one word command!", e.getMessage());
        }
    }
}
