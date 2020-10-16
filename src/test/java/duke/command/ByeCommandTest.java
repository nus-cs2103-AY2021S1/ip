package duke.command;

import org.junit.jupiter.api.Test;

import duke.Ui;

public class ByeCommandTest extends CommandTest {
    /**
     * Tests if the Bye Command outputs the correct message.
     * @throws Exception if the Bye Command fails to output the expected message.
     */
    @Test
    public void testCommandExecution() throws Exception {
        ByeCommand byeCommand = new ByeCommand();
        String output = byeCommand.execute();
        if (!output.equals(Ui.bye())) {
            throw new Exception("Bye command output is incorrect!");
        }
        System.out.println("Test passed");
    }
}
