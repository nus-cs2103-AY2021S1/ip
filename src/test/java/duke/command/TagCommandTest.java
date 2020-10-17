package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.ui.Ui;
import duke.parser.TagParser;


public class TagCommandTest extends CommandTest {
    /**
     * Tests if the TagCommand provides the correct output given a TagParser that parsed an invalid tag command.
     */
    @Test
    public void execute_invalidTagCommand_correctOutput() {
        setLines();
        try {
            TagParser invalidTagParser = new TagParser("tag 4 urgent", lines);
            TagCommand invalidTagCommand = new TagCommand(lines, invalidTagParser);
            String invalidOutput = invalidTagCommand.execute();
            assertEquals(Ui.handleDukeException(new DukeException("No such task exists!")), invalidOutput);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }

    /**
     * Tests if the TagCommand provides the correct output given a TagParser that parsed a valid tag command.
     */
    @Test
    public void execute_validTagCommand_correctOutput() {
        setLines();
        try {
            TagParser validTagParser = new TagParser("tag 2 urgent", lines);
            TagCommand validTagCommand = new TagCommand(lines, validTagParser);
            String validOutput = validTagCommand.execute();
            assertEquals(Ui.taggedTask(lines.getTask(1), true), validOutput);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }
}
