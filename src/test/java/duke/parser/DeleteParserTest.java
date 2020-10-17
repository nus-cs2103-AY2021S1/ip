package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeleteParserTest extends ParserTest {
    /**
     * Tests if DeleteParser parses an invalid delete command correctly with its checkIfValid().
     */
    @Test
    public void checkIfValid_invalidDeleteCommand_correctOutput() {
        setLines();
        DeleteParser invalidDeleteParser = new DeleteParser("delete 4", lines);
        try {
            int invalidIndex = invalidDeleteParser.checkIfValid();
            fail();
        } catch (Exception e) {
            assertEquals("Hey, no such task exists!", e.getMessage());
        }
        resetLines();
    }

    /**
     * Tests if DeleteParser parses a valid delete command correctly with its checkIfValid().
     */
    @Test
    public void checkIfValid_validDeleteCommand_correctOutput() {
        setLines();
        DeleteParser invalidDeleteParser = new DeleteParser("delete 2", lines);
        try {
            int validIndex = invalidDeleteParser.checkIfValid();
            assertEquals(2, validIndex);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }


}
