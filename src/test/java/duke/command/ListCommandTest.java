package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListCommandTest {

    @Test
    public void isExit_listCommand_false() {
        ListCommand listCommand = new ListCommand();
        assertEquals(false, listCommand.isExit());
    }
}
