package focus.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {
    private final ExitCommand exitCommand = new ExitCommand();

    @Test
    public void testIsExit() {
        assertTrue(exitCommand.isExit());
    }
}
