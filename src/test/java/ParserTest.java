import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/**
 * Represents Parser Test class and consists of methods to test the Parser.
 */
public class ParserTest {

    /**
     * Whether the input match the expected output.
     */
    @Test
    void processInputParserTest() {
        try {
            Parser.processInput("hey");
        } catch (InvalidException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-( \n"
                    + "You can you \"help\" command to find the list of command to use", e.getMessage());
        }
    }
    /**
     * Whether the user can exit successfully.
     */
    @Test
    void exitParserTest() {
        assertTrue(Parser.exit("bye"));
    }
}
