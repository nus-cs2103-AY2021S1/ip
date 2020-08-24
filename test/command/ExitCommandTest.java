package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExitCommandTest {

    @Test
    void isModifying_false() {
        assertFalse(new ExitCommand().hasUndo());
    }

    @Test
    void isExit_true() {
        assertTrue(new ExitCommand().isExit());
    }
}