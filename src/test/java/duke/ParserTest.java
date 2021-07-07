package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void testParse_listCommand() throws DukeException {
        try {
            Command c = Parser.parse("list");
            assertTrue(c instanceof ListCommand);
        } catch (DukeException e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void testParse_invalidCommand() throws DukeException {
        try {
            Command c = Parser.parse("rtdcfghuiygtfcg");

        } catch (DukeException e) {
            assertEquals("OOPS!!! Sorry, I'm not sure what that means :(", e.getMessage());
        }
    }
}
