import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void testCommandExecution() {
        String expected = new CommandResult("Bye. Hope to see you again soon!").responseToUser;
        String actual = new ExitCommand("bye").execute(new TaskList(), new Storage()).responseToUser;
        assertEquals(expected, actual);
    }

    @Test
    public void unknownCommand_executeUnknownCommand_exceptionThrown() {
        try {
            new Command("test").execute(new TaskList(), new Storage());
        } catch (DukeException e) {
            assertEquals("This method is to be implemented by child classes",
                    e.getMessage()
            );
        }
    }
}
