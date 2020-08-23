package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ByeCommandTest {

    @Test
    public void isExit_byeCommand_true() {
        ByeCommand byeCommand = new ByeCommand();
        assertEquals(true, byeCommand.isExit());
    }
}
