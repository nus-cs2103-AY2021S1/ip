package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void isExit_listCommand_false() {
        ListCommand listCommand = new ListCommand();
        assertEquals(listCommand.isExit(), false);
    }
}
