package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {
    @Test
    public void isExit() {
        assertEquals(true, new ByeCommand().isExit());
    }
}
