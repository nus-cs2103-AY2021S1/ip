package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class DeleteCommandTest {

    @Test
    public void isExit_deleteCommand_false() {
        DeleteCommand deleteCommand = new DeleteCommand(1);
        assertFalse(deleteCommand.isExit());
    }
}
