import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ByeCommandTest {

    @Test
    void isExitTest() {
        assertEquals(new ByeCommand().isExit(), true);
    }

}
