import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {

    protected Task event = new Event("project meeting", "17/7/2015", "1842");
    protected String testCommand = "testCommand";

    public ParserTest() throws DukeException {
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() {
        try {
            new Parser();
            Parser.parse(testCommand);
            fail();
        } catch (DukeException error) {
            assertEquals("Catastrophe detected! I'm sorry, but '" + testCommand
                    + "' is not within my realm of knowledge. " + "\u2620 ", error.getMessage());
        }
    }

    @Test
    public void setDone_validInput_success() {
        String correctOutput = "[E][\u2714] project meeting (at: 17th of July 2015, 6:42pm)";
        event.setDone();
        assertEquals(event.toString(), correctOutput);
    }

    @Test
    public void doneCommand_missingIndex_exceptionThrown() {
        try {
            new Parser();
            Parser.parse("done");
            fail();
        } catch (DukeException error) {
            assertEquals("Please specify which task you have completed.", error.getMessage());
        }
    }

    @Test
    public void eventCommand_missingTiming_exceptionThrown() {
        try {
            new Parser();
            Parser.parse("event project meeting /at 4/1/2018");
            fail();
        } catch (DukeException error) {
            assertEquals("Date and Timing fields has not been specified correctly. Please try again.",
                         error.getMessage());
        }
    }
}
