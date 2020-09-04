package duke.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ByeCommandTest {
    @Test
    public void isExit_alwaysTrue() {
        ByeCommand b = new ByeCommand("bye");
        assertTrue(b.isExit());
    }
}
