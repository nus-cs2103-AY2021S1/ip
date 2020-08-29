package duke.commands;

import static duke.utils.Messages.MESSAGE_BYE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasklist.TaskList;

public class ByeCommandTest {

    @Test
    public void testExecute() {
        ByeCommand command = new ByeCommand();
        CommandResult actual = command.execute(new TaskList());
        String response = MESSAGE_BYE;
        CommandResult expected = new CommandResult(response, true);
        assertEquals(expected, actual);
    }
}
