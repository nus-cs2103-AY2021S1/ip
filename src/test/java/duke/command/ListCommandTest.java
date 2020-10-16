package duke.command;

import org.junit.jupiter.api.Test;

import duke.Ui;

public class ListCommandTest extends CommandTest {
    /**
     * Tests if the list command works as expected.
     * @throws Exception if the output of the list command is not correct.
     */
    @Test
    public void testCommandExecution() throws Exception {
        setLines();
        ListCommand listCommand = new ListCommand(lines);
        String output = listCommand.execute();
        if (!output.equals(Ui.listTasks(lines.getList()))) {
            throw new Exception("List command not working properly");
        }
        System.out.println("All tests passed");
    }
}
