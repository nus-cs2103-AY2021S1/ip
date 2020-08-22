package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {
    @Test
    public void isExit_byeCommand_true() {
        ByeCommand byeCommand = new ByeCommand();
        assertEquals(byeCommand.isExit(), true);
    }
}
