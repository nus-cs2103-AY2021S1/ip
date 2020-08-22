package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void isExit_todoCommand_false() {
        TodoCommand todoCommand = new TodoCommand("test");
        assertEquals(false, todoCommand.isExit());
    }
}
