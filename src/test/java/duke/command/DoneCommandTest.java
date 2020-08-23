package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class DoneCommandTest {

    @Test
    public void isExit_doneCommand_false() {
        DoneCommand doneCommand = new DoneCommand(1);
        assertFalse(doneCommand.isExit());
    }
}
