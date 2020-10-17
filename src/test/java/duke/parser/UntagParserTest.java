package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class UntagParserTest extends ParserTest {

    /**
     * Tests if UntagParser parses an invalid untag command properly with its checkIfValid().
     */
    @Test
    public void checkIfValid_invalidUntagCommand_correctOutput() {
        setLines();
        UntagParser invalidUntagParser = new UntagParser("untag ", lines);
        try {
            int invalidIndex = invalidUntagParser.checkIfValid();
            fail();
        } catch (Exception e) {
            assertEquals("Which task are you trying to untag?", e.getMessage());
        }
        resetLines();
    }

    /**
     * Tests if UntagParser parses a valid untag command properly with its checkIfValid().
     */
    @Test
    public void checkIfValid_validUntagCommand_correctOutput() {
        setLines();
        UntagParser validUntagParser = new UntagParser("untag 3", lines);
        try {
            int validIndex = validUntagParser.checkIfValid();
            assertEquals(3, validIndex);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }
}
