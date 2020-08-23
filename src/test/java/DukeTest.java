import exceptions.DukeMissingTimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DukeTest {

    @Test
    public void handleDone_exceptionThrown() {
        Duke duke = new Duke();
        assertThrows(DukeMissingTimeException.class, () -> duke.handleDeadline("notime"));
    }

    @Test
    public void handleEvent_exceptionThrown() {
        Duke duke = new Duke();
        assertThrows(DukeMissingTimeException.class, () -> duke.handleEvent("notime"));
    }
}
