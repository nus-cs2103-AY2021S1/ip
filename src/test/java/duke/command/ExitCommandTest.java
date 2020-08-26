package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {

    @Test
    public void isExitTest() {
        assertTrue(new ExitCommand().isExit());
    }
}
