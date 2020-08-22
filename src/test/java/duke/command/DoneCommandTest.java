package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    @Test
    public void isExit_doneCommand_false() {
        DoneCommand doneCommand = new DoneCommand(1);
        assertEquals(doneCommand.isExit(), false);
    }
}
