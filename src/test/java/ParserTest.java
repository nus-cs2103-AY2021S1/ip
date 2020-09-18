import duke.util.DukeException;
import duke.util.Parser;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.Assert.assertEquals;

/**
 * Testing class for Parser object.
 */
public class ParserTest {

    @Test
    public void ParseRemainingStringTest() throws DukeException {
        String remainingString = " CS2103T assignment /by 2020-08-22 2359";
        String[] actual = Parser.parseAddCommandString("deadline", remainingString);
        String[] expected = new String[] {"deadline", "CS2103T assignment", "2020-08-22 2359"};
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
    }
}
