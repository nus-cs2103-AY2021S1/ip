package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;




public class UiTest {
    @Test
    public void constructor_validCreation_success() {
        assertTrue(new Ui(new Parser(new TaskList(new ArrayList<>()))).checkDukeStatus());
    }

    @Test
    public void stopDuke_setStatusToFalse_success() {
        Ui stopDuke = new Ui(new Parser(new TaskList(new ArrayList<>())));
        stopDuke.discontinue();
        assertFalse(stopDuke.checkDukeStatus());
    }
}
