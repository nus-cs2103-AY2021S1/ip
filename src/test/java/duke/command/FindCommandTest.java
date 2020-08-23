package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FindCommandTest {
    @Test
    public void isExit__alwaysFalse() {
        assertFalse(new FindCommand("find this").isExit());
        assertFalse(new FindCommand("find ").isExit());
    }
}
