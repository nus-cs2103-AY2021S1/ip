package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class InvalidCommandTest {

    @Test
    void isModifying_false() {
        assertFalse(new InvalidCommand().hasUndo());
    }

    @Test
    void isExit_false() {
        assertFalse(new InvalidCommand().isExit());
    }

}