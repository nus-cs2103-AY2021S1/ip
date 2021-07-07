package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains a test case to test the load method in the Parser class.
 */
public class ParserTest {
    @Test
    public void Test() {
        String input = "event";
        Parser parser = new Parser();
        Instruction instruction = parser.load(input);
        assertEquals(instruction, Instruction.EVENT);
    }
}