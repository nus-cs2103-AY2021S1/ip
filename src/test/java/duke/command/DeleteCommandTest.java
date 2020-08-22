package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void isExit_deleteCommand_false() {
        DeleteCommand deleteCommand = new DeleteCommand(1);
        assertEquals(deleteCommand.isExit(), false);
    }
}
