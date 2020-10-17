package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DoneParserTest extends ParserTest {
    /**
     * Tests if DoneParser parses an invalid done command properly with its checkIfValid().
     */
    @Test
    public void checkIfValid_invalidDoneCommand_correctOutput() {
        setLines();
        DoneParser invalidDoneParser = new DoneParser("done 0", lines);
        try {
            int invalidOutcome = invalidDoneParser.checkIfValid();
            fail();
        } catch (Exception e) {
            assertEquals("Hey, no such task exists!", e.getMessage());
        }
        resetLines();
    }

    /**
     * Tests if DoneParser parses a valid done command properly with its checkIfValid().
     */
    @Test
    public void checkIfValid_validDoneCommand_correctOutput() {
        setLines();
        DoneParser validDoneParser = new DoneParser("done 2", lines);
        try {
            int validOutcome = validDoneParser.checkIfValid();
            assertEquals(2, validOutcome);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }
}
