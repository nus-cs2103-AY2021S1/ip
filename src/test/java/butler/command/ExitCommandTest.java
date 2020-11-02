package butler.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {

    @Test
    public void isExitTest() {
        ExitCommand c = new ExitCommand();
        assertEquals(true, c.isExit());
    }
}
