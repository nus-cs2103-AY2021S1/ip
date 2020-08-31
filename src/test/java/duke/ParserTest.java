package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

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

    @Test
    void testParserInvalidCommand() {
        assertEquals(
                Parser.Command.INVALID,
                Parser.parseCommand("im not sure")
        );
        assertEquals(
                Parser.Command.INVALID,
                Parser.parseCommand("even t concert /at tonight!")
        );
    }

}
