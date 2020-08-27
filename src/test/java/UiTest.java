import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui ui = new Ui();

    @Test
    public void testGetServantSpeak() {
        assertEquals("Duke:\n", ui.getServantSpeak());
    }

    @Test
    public void testGetUserPrompt() {
        assertEquals("Your Command Sire:", ui.getUserPrompt());
    }
}
