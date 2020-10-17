package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TagParserTest extends ParserTest {
    /**
     * Tests if TagParser parses an invalid tag command properly with its  checkIfValid().
     */
    @Test
    public void checkIfValid_invalidTagCommand_correctOutput() {
        setLines();
        TagParser invalidTagParser = new TagParser("tag 10 important", lines);
        try {
            int invalidIndex = invalidTagParser.checkIfValid();
            fail();
        } catch (Exception e) {
            assertEquals("No such task exists!", e.getMessage());
        }
        resetLines();
    }

    /**
     * Tests if TagParser parses a valid tag command properly with its checkIfValid().
     */
    @Test
    public void checkIfValid_validTagCommand_correctOutput() {
        setLines();
        TagParser validTagParser = new TagParser("tag 1 important", lines);
        try {
            int validIndex = validTagParser.checkIfValid();
            assertEquals(1, validIndex);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }
}
