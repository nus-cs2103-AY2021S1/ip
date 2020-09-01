package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {
    @Test
    public void execute() {
        String actual = ExitCommand.execute();
        String expected = "Bye. Hope to see you again soon!";
        assertEquals(expected, actual);
    }
}
