package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.ui.Ui;
import duke.parser.DeleteParser;


public class DeleteCommandTest extends CommandTest {

    /**
     * Tests if DeleteCommand provides the correct output given a DeleteParser that parsed an invalid delete command.
     */
    @Test
    public void execute_invalidDeleteCommand_correctOutput() {
        setLines();
        try {
            DeleteParser invalidDeleteCommandParser = new DeleteParser("delete 4", lines);
            DeleteCommand invalidDeleteCommand = new DeleteCommand(lines, invalidDeleteCommandParser);
            String invalidOutput = invalidDeleteCommand.execute();
            assertEquals(Ui.handleDukeException(new DukeException("Hey, no such task exists!")), invalidOutput);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }

    /**
     * Tests if DeleteCommand provides the correct output given a DeleteParser that parsed a valid delete command.
     */
    @Test
    public void execute_validDeleteCommand_correctOutput() {
        setLines();
        try {
            DeleteParser validDeleteCommandParser = new DeleteParser("delete 1", lines);
            DeleteCommand validDeleteCommand = new DeleteCommand(lines, validDeleteCommandParser);
            String deletedTask = lines.getTask(0);
            String validOutput = validDeleteCommand.execute();
            assertEquals(Ui.deletedTask(deletedTask, 2), validOutput);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }
}
