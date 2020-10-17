package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class FindParserTest extends ParserTest {
    /**
     * Tests if FindParser parses an invalid find command properly with its keywordIfValid().
     */
    @Test
    public void keywordIfValid_invalidFindCommand_correctOutput() {
        FindParser invalidFindParser = new FindParser("find");
        try {
            String invalidKeyword = invalidFindParser.keywordIfValid();
            fail();
        } catch (Exception e) {
            assertEquals("What are you trying to find?", e.getMessage());
        }
    }

    /**
     * Tests if FindParser parses a valid find command properly with its keywordIfValid().
     */
    @Test
    public void keywordIfValid_validFindCommand_correctOutput() {
        FindParser validFindParser = new FindParser("find ome");
        try {
            String validKeyword = validFindParser.keywordIfValid();
            assertEquals("ome", validKeyword);
        } catch (Exception e) {
            fail();
        }
    }
}
