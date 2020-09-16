package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.parser.Parser;

/**
 * JUnit test class to test methods in Parser.
 */
class ParserTest {

    /**
     * Tests Parser if it returns the correct formatted date and time
     * provided the input date/time matches the format.
     */
    @Test
    void testParserCorrectTime() {
        String correctFormatTime = "random description /by 20-01-2021 2359";
        String incorrectFormatTime = "random description /by ASAP!!!";
        try {
            String[] parsedTime1 = Parser.parseTimedTask(correctFormatTime);
            assertEquals("random description", parsedTime1[0]);
            assertEquals("JANUARY 20 2021 11:59 PM", parsedTime1[1]);

            String[] parsedTime2 = Parser.parseTimedTask(incorrectFormatTime);
            assertEquals("random description", parsedTime2[0]);
            assertEquals("ASAP!!!", parsedTime2[1]);

        } catch (DukeException e) {
            System.out.println(e);
            fail();
        }
    }

    /**
     * Tests the parseCommand method for invalid Commands.
     */
    @Test
    void testParserInvalidCommand() {
        try {
            Parser.getCommand("im not sure", new TaskList(), new Storage("")).executeWithResponse();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.toString());
        }
        try {
            Parser.getCommand("even t concert /at tonight!", new TaskList(), new Storage("")).executeWithResponse();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.toString());
        }
    }
}
