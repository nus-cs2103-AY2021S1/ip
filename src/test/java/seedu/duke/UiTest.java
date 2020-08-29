package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void constructor_validCreation_success() {
        assertEquals(true, new Ui(new Parser(new TaskList(new ArrayList<Task>()))).checkDukeStatus());
    }

    @Test
    public void stopDuke_setStatusToFalse_success() {
        Ui stopDuke = new Ui(new Parser(new TaskList(new ArrayList<Task>())));
        stopDuke.disContinue();
        assertEquals(false, stopDuke.checkDukeStatus());
    }
}
