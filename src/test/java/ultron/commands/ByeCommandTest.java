package ultron.commands;

import org.junit.jupiter.api.Test;

public class ByeCommandTest {
    /**
     * Check if the isExit method returning the correct value.
     */
    @Test
    public void isExitTest() {
        ByeCommand byeCommand = new ByeCommand("");
        assert byeCommand.isExit();
    }
}
