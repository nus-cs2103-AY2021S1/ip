import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Ui;

/**
 * Tests Ui output.
 */
public class UiTest {
    @Test
    public void greetingMessage_messageSuccess() {
        Ui ui = new Ui();
        assertEquals("SAYONARA!",
                     ui.sayGoodbye());
    }
}
