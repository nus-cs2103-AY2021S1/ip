package duke.command;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {
    @Test
    public void testIsExit() {
        assert(new ExitCommand().isExit());
    }
}
