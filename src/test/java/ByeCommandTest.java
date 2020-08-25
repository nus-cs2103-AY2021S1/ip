import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ByeCommandTest {

    @Test
    void isExitTest() {
        assertEquals(new ByeCommand().isExit(), true);
    }

}