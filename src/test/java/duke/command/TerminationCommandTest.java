package duke.command;

import main.java.duke.command.TerminationCommand;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerminationCommandTest {
    @Test
    void isExit_normalTermination_trueReturned() {
        assertEquals(true, new TerminationCommand().isExit());
    }
}
