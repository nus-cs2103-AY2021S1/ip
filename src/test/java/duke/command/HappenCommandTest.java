package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class HappenCommandTest {
    @Test
    public void isExit_alwaysFalse() {
        assertFalse(new HappenCommand("happen on today").isExit());
    }
}
