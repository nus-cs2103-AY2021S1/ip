package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ListCommandTest {

    @Test
    public void isExit_listCommand_false() {
        ListCommand listCommand = new ListCommand();
        assertFalse(listCommand.isExit());
    }
}
