package parser;

import duke.DukeException;
import duke.parser.Parser;
import duke.command.Command;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseTest() {
        try {
            Command command = Parser.parse("list /by 2020-04-04");
            Command actual = new ListCommand("2020-04-04");
            assertEquals(command.toString(), actual.toString());
        } catch (DukeException e) {
            fail("Exception thrown! " + e);
        }
    }
}
