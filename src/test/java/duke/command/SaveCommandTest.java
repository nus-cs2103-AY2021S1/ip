package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveCommandTest {
    @Test
    public void isExit_saveCommand_false() {
        SaveCommand saveCommand = new SaveCommand();
        assertEquals(saveCommand.isExit(), false);
    }

}
