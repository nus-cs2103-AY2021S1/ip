package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class FindCommandTest {

    @Test
    public void isExit_todoCommand_false() {
        FindCommand findCommand = new FindCommand("test");
        assertFalse(findCommand.isExit());
    }
}
