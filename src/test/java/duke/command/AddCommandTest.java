package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddCommandTest {
    @Test
    public void isExit_alwaysFalse() {
        assertFalse(new AddCommand("anything").isExit());
        assertFalse(new AddCommand("todo anything").isExit());
        assertFalse(new AddCommand("event anything").isExit());
        assertFalse(new AddCommand("deadline anything").isExit());
    }
}
