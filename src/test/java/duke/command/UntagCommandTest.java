package duke.command;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Ui;
import duke.parser.UntagParser;


public class UntagCommandTest extends CommandTest {
    /**
     * Tests if the untag command works as expected
     * @throws Exception if the output is not as expected
     */
    @Test
    public void testCommandExecution() throws Exception {
        setLines();
        lines.tagItem(1, "urgent");
        UntagParser invalidUntagParser = new UntagParser("untag 0", lines);
        UntagCommand invalidUntagCommand = new UntagCommand(lines, invalidUntagParser);
        String invalidOutput = invalidUntagCommand.execute();
        if (!invalidOutput.equals(Ui.handleDukeException(new DukeException("No such task exists!")))) {
            throw new Exception("Untag command failed to output expected error message upon invalid index");
        }
        UntagParser validUntagParser = new UntagParser("untag 1", lines);
        UntagCommand validUntagCommand = new UntagCommand(lines, validUntagParser);
        String validOutput = validUntagCommand.execute();
        System.out.println(validOutput);
        if (!validOutput.equals(Ui.untaggedTask(lines.getTask(1), false))) {
            throw new Exception("Untag command failed to achieve desired output");
        }
        System.out.println("All tests passed");
    }
}
