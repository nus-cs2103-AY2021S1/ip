package butler.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintCommandTest {

    @Test
    public void isExitTest() {
        PrintCommand c = new PrintCommand();
        assertEquals(false, c.isExit()) ;
    }
}
