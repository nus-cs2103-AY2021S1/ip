package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.ui.Ui;
import duke.parser.DoneParser;

public class DoneCommandTest extends CommandTest {
    /**
     * Tests if DoneCommand provides the correct output given a DoneParser that parsed an invalid input
     */
    @Test
    public void execute_invalidDoneCommand_correctOutput() {
        setLines();
        try {
            DoneParser invalidDoneCommandParser = new DoneParser("done 0", lines);
            DoneCommand invalidDoneCommand = new DoneCommand(lines, invalidDoneCommandParser);
            String invalidOutput = invalidDoneCommand.execute();
            assertEquals(Ui.handleDukeException(new DukeException("Hey, no such task exists!")), invalidOutput);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }

    /**
     * Tests if DoneCommand provides the correct output given a DoneParser that parsed a valid input
     */
    @Test
    public void execute_validDoneCommand_correctOutput() {
        setLines();
        try {
            DoneParser validDoneCommandParser = new DoneParser("done 3", lines);
            DoneCommand validDoneCommand = new DoneCommand(lines, validDoneCommandParser);
            String validOutput = validDoneCommand.execute();
            assertEquals(Ui.done(lines.getTask(2)), validOutput);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }
}
