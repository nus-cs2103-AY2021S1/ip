package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class SaveCommandTest {

    @Test
    public void isExit_saveCommand_false() {
        SaveCommand saveCommand = new SaveCommand();
        assertFalse(saveCommand.isExit());
    }

}
