import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



public class ParserTest {

    @Test
    void processInputParserTest() {
        try {
            Parser.processInput("hey");
        } catch (InvalidException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-( "
                    + "You can you \"help\" command to find the list of command to use", e.getMessage());
        }
    }
    @Test
    void exitParserTest() {
        assertTrue(Parser.exit("bye"));
    }
}
