import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ByeCommandTest {

    @Test
    void isExitTest_getIsExitOfByeCmd_returnsTrue() {
        assertEquals(new ByeCommand().isExit(), true);
    }

}
