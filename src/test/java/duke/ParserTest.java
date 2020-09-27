package duke;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void deadlineParsing_goodFormat_success() throws DukeException {
        String command = "deadline my birthday!! /by 2020-09-12";
        ParsedCommand pc = new ParsedCommand("deadline").withName("my birthday!!").withDate("2020-09-12");


        Parser parser = new Parser();
        assertEquals(pc, parser.parseNextCommand(command));
    }

    @Test
    public void deadlineParsing_badDateFormat_exceptionThrown() {
        String command = "deadline hurr durr / date 2020-02-02";
        Parser parser = new Parser();

        try {
            assertEquals(new ParsedCommand("deadline"), parser.parseNextCommand(command));
            fail(); // test shouldn't reach this line
        } catch (DukeException e) {
            assertEquals("Please use the format: deadline <name> /by <yyyy-mm-dd>", e.getMessage());
        }
    }



}
