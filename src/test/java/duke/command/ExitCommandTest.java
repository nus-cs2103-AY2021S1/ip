package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {
    private final ExitCommand exitCommand = new ExitCommand();

    @Test
    public void testIsExit() {
        assertTrue(exitCommand.isExit());
    }
}
