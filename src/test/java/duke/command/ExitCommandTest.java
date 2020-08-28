package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
    @Test
    public void execute() {
        String actual = ExitCommand.execute();
        String expected = "Bye. Hope to see you again soon!";
        assertEquals(expected, actual);
    }
}
