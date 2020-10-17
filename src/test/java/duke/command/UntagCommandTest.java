package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.ui.Ui;
import duke.parser.UntagParser;


public class UntagCommandTest extends CommandTest {
    /**
     * Tests if the UntagCommand provides the correct output given a UntagParser that parsed an invalid untag command.
     */
    @Test
    public void execute_invalidUntagCommand_correctOutput() {
        setLines();
        try {
            lines.tagItem(1, "urgent");
            UntagParser invalidUntagParser = new UntagParser("untag 0", lines);
            UntagCommand invalidUntagCommand = new UntagCommand(lines, invalidUntagParser);
            String invalidOutput = invalidUntagCommand.execute();
            assertEquals(Ui.handleDukeException(new DukeException("No such task exists!")), invalidOutput);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }

    /**
     * Tests if the UntagCommand provides the correct output given a UntagParser that parsed a valid untag command.
     */
    @Test
    public void execute_validUntagCommand_correctOutput() {
        setLines();
        try {
            lines.tagItem(1, "urgent");
            UntagParser validUntagParser = new UntagParser("untag 1", lines);
            UntagCommand validUntagCommand = new UntagCommand(lines, validUntagParser);
            String validOutput = validUntagCommand.execute();
            assertEquals(Ui.untaggedTask(lines.getTask(1), false), validOutput);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }
}
