import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    void processInput_Parser_Test() {
        try {
            Parser.processInput("hey");
        } catch (InvalidException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
    
    @Test
    void exit_Parser_Test() {
        assertTrue(Parser.exit("bye"));
    }
}
