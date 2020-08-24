package duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {
    @Test
    public void isExitTrue_byeCommandCreated_true() {
        Command test = new ByeCommand();
        assertTrue(test.isExit());
    }

}
