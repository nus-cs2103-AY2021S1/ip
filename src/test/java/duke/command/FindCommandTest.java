package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.ui.Ui;
import duke.parser.FindParser;




public class FindCommandTest extends CommandTest {

    /**
     * Tests if FindCommand provides the correct output given a FindParser that parsed an invalid find command.
     */
    @Test
    public void execute_invalidFindCommand_correctOutput() {
        setLines();
        try {
            FindParser invalidFindParser = new FindParser("find NUS");
            FindCommand invalidFindCommand = new FindCommand(lines, invalidFindParser);
            String invalidOutput = invalidFindCommand.execute();
            assertEquals(Ui.listMatchingTasks(new ArrayList<>()), invalidOutput);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }

    /**
     * Tests if FindCommand provides the correct output given a FindParser that parsed a valid find command.
     */
    @Test
    public void execute_validFindCommand_correctOutput() {
        setLines();
        try {
            FindParser validFindParser = new FindParser("find e");
            FindCommand validFindCommand = new FindCommand(lines, validFindParser);
            String validOutput = validFindCommand.execute();
            assertEquals(Ui.listMatchingTasks(lines.getList()), validOutput);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }
}
