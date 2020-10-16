package duke.command;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Ui;
import duke.parser.DeleteParser;


public class DeleteCommandTest extends CommandTest {

    /**
     *  Tests if DeleteCommand executes the right commands given DeleteParser's parsing.
     * @throws Exception if the output is not as expected.
     */
    @Test
    public void testCommandExecution() throws Exception {
        setLines();
        DeleteParser invalidDeleteCommandParser = new DeleteParser("delete 4", lines);
        DeleteCommand invalidDeleteCommand = new DeleteCommand(lines, invalidDeleteCommandParser);
        String invalidOutput = invalidDeleteCommand.execute();
        if (!invalidOutput.equals(Ui.handleDukeException(new DukeException("Hey, no such task exists!")))) {
            throw new Exception("Invalid index not caught");
        }
        resetLines();
        setLines();
        DeleteParser validDeleteCommandParser = new DeleteParser("delete 1", lines);
        DeleteCommand validDeleteCommand = new DeleteCommand(lines, validDeleteCommandParser);
        String deletedTask = lines.getTask(0);
        String validOutput = validDeleteCommand.execute();
        if (!validOutput.equals(Ui.deletedTask(deletedTask, 2))) {
            throw new Exception("Valid delete command not executed properly");
        }
        System.out.println("All Tests Passed");
    }
}
