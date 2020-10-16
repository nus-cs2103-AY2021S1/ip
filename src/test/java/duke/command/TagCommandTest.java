package duke.command;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Ui;
import duke.parser.TagParser;


public class TagCommandTest extends CommandTest {
    /**
     * Tests if the TagCommand works as expected.
     * @throws Exception if the TagCommand's output is not as expected.
     */
    @Test
    public void testCommandExecution() throws Exception {
        setLines();
        TagParser invalidTagParser = new TagParser("tag 4 urgent", lines);
        TagCommand invalidTagCommand = new TagCommand(lines, invalidTagParser);
        String invalidOutput = invalidTagCommand.execute();
        if (!invalidOutput.equals(Ui.handleDukeException(new DukeException("No such task exists!")))) {
            throw new Exception("Tag command failed to detect invalid index");
        }
        resetLines();
        setLines();
        TagParser validTagParser = new TagParser("tag 2 urgent", lines);
        TagCommand validTagCommand = new TagCommand(lines, validTagParser);
        String validOutput = validTagCommand.execute();
        if (!validOutput.equals(Ui.taggedTask(lines.getTask(1), true))) {
            throw new Exception("Tag command failed to tag the task at the specified valid index");
        }
        System.out.println("All tests passed");
    }
}
