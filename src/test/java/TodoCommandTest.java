import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoCommandTest {

    @Test
    void isExitTest() {
        String[] input = "test case".split("\\s");
        assertEquals(new TodoCommand(input).isExit(), false);
    }

}