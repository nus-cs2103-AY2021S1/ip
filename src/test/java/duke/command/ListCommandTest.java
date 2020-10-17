package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.ui.Ui;

public class ListCommandTest extends CommandTest {
    /**
     * Tests if the ListCommand provides the correct output given a TaskList lines.
     */
    @Test
    public void execute_validListCommand_correctOutput() {
        setLines();
        try {
            ListCommand listCommand = new ListCommand(lines);
            String output = listCommand.execute();
            assertEquals(Ui.listTasks(lines.getList()), output);
        } catch (Exception e) {
            fail();
        }
        resetLines();
    }
}
