package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteCommandTest {
    @Test
    public void isExit_alwaysFalse() {
        assertFalse(new DeleteCommand("delete ").isExit());
        assertFalse(new DeleteCommand("delete 3").isExit());
        assertFalse(new DeleteCommand("delete 0").isExit());
        assertFalse(new DeleteCommand("delete -3").isExit());
        assertFalse(new DeleteCommand("delete anything").isExit());
    }
}
