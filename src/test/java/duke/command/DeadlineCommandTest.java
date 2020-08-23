package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineCommandTest {

    @Test
    public void isExit_deadlineCommand_false() {
        DeadlineCommand deadlineCommand = new DeadlineCommand("test", "2020-08-22");
        assertEquals(false, deadlineCommand.isExit());
    }
}
