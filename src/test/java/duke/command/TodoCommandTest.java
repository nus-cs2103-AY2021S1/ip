package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class TodoCommandTest {

    @Test
    public void isExit_todoCommand_false() {
        TodoCommand todoCommand = new TodoCommand("test");
        assertFalse(todoCommand.isExit());
    }
}
