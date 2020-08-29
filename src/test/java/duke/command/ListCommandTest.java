package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ListCommandTest {
    @Test
    public void isExit_alwaysFalse() {
        assertFalse(new ListCommand("list").isExit());
    }
}
