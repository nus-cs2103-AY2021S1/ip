package seedu.duke;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
