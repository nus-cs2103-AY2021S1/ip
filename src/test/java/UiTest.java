import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.siawsam.duke.Ui;

public class UiTest {
    @Test
    public void ui_onStartup_displayWelcomeMessage() {
        assertEquals(
                "Hi I'm Duke, your personal task-tracker bot!\n"
                        + "You can add todos, deadlines, or events to my list.",
                Ui.showWelcomeMessage()
        );
    }
    
    @Test
    public void ui_noExistingSave_displayNoExistingSaveMessage() {
        assertEquals("You don't have an existing saved task list.",
                     Ui.showNoExistingSave()
        );
    }
}
