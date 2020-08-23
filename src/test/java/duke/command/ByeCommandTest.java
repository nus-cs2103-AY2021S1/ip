package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {
    @Test
    public void isExit_alwaysTrue() {
        ByeCommand b = new ByeCommand("bye");
        assertTrue(b.isExit());
    }
}
