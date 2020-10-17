package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import duke.command.Command;

public class DukeParserTest extends ParserTest {
    /**
     * Tests if DukeParser parses an invalid input command properly with its parse().
     */
    @Test
    public void parse_invalidInputCommand_correctOutput() {
        setLines();
        DukeParser dukeParser = new DukeParser(lines.getList());
        try {
            Command invalidCommand = dukeParser.parse("#tag 2 first");
            fail();
        } catch (Exception e) {
            assertEquals("Your input contains the # character, don't do that :(", e.getMessage());
        }
        resetLines();
    }

    /**
     * Tests if DukeParser parses a valid input command properly with its parse().
     */
    @Test
    public void parse_validInputCommand_correctOutput() {
        setLines();
        DukeParser dukeParser = new DukeParser(lines.getList());
        try {
            Command validCommand = dukeParser.parse("done 2");
            assertEquals(Ui.done(lines.getTask(2)), validCommand.execute());
        } catch (Exception e) {
            fail();
        }
    }

}
