package butler.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListCommandTest {

    @Test
    public void isExitTest() {
        ListCommand c = new ListCommand();
        assertEquals(false, c.isExit());
    }
}
