package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ListCommandTest {
    @Test
    public void isExit_alwaysFalse() {
        assertFalse(new ListCommand("list").isExit());
    }
}
