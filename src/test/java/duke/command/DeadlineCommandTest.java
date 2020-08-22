package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    @Test
    public void isExit_deadlineCommand_false() {
        DeadlineCommand deadlineCommand = new DeadlineCommand("test", "2020-08-22");
        assertEquals(deadlineCommand.isExit(), false);
    }
}
