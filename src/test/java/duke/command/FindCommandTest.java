package duke.command;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.Ui;
import duke.parser.FindParser;




public class FindCommandTest extends CommandTest {

    /**
     * Tests if the find command achieves the desired function and output.
     * @throws Exception if the outcome is not as expected.
     */
    @Test
    public void testCommandExecution() throws Exception {
        setLines();
        FindParser invalidFindParser = new FindParser("find NUS");
        FindCommand invalidFindCommand = new FindCommand(lines, invalidFindParser);
        String invalidOutput = invalidFindCommand.execute();
        if (!invalidOutput.equals(Ui.listMatchingTasks(new ArrayList<>()))) {
            throw new Exception("Invalid command failed to achieve desired output");
        }
        resetLines();
        setLines();
        FindParser validFindParser = new FindParser("find e");
        FindCommand validFindCommand = new FindCommand(lines, validFindParser);
        String validOutput = validFindCommand.execute();
        if (!validOutput.equals(Ui.listMatchingTasks(lines.getList()))) {
            throw new Exception("Valid list command failed to achieve desired output");
        }
        System.out.println("All tests passed");
    }
}
