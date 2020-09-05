package duke.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class UpdateCommandTest {

    @Test
    public void isExit_updateCommand_false() {
        UpdateCommand updateCommand = new UpdateCommand(1, "test");
        assertFalse(updateCommand.isExit());
    }
}
