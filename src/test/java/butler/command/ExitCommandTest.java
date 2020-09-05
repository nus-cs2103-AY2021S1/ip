package butler.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {

    @Test
    public void isExitTest() {
        ExitCommand c = new ExitCommand();
        assertEquals(true, c.isExit()) ;
    }
}
