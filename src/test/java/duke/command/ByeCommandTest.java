package duke.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ByeCommandTest {
    @Test
    public void isExitTrue_byeCommandCreated_true() {
        Command test = new ByeCommand();
        assertTrue(test.isExit());
    }

}
