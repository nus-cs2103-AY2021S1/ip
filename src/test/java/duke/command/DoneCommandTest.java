package duke.command;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Ui;
import duke.parser.DoneParser;

public class DoneCommandTest extends CommandTest {
    /**
     * Tests if DoneCommand executes the right commands given DoneParser's parsing.
     * @throws Exception if the output is not as expected
     */
    @Test
    public void testCommandExecution() throws Exception {
        setLines();
        DoneParser invalidDoneCommandParser = new DoneParser("done 0", lines);
        DoneCommand invalidDoneCommand = new DoneCommand(lines, invalidDoneCommandParser);
        String invalidOutput = invalidDoneCommand.execute();
        if (!invalidOutput.equals(Ui.handleDukeException(new DukeException("Hey, no such task exists!")))) {
            throw new Exception("Invalid index not caught");
        }
        resetLines();
        setLines();
        DoneParser validDoneCommandParser = new DoneParser("done 3", lines);
        DoneCommand validDoneCommand = new DoneCommand(lines, validDoneCommandParser);
        String validOutput = validDoneCommand.execute();
        if (!validOutput.equals(Ui.done(lines.getTask(2)))) {
            throw new Exception("Valid done command not executed properly");
        }
        System.out.println("All Tests Passed");
    }
}
