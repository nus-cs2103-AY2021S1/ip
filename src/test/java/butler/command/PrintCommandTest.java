package butler.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PrintCommandTest {

    @Test
    public void isExitTest() {
        PrintCommand c = new PrintCommand();
        assertEquals(false, c.isExit());
    }
}
