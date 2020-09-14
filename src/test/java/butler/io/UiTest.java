package butler.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void showTaskIsDeletedTest() {
        Ui ui = new Ui();
        String expectedOutput = "Task 1 has been deleted.";
        assertEquals(expectedOutput, ui.showTaskIsDeleted(1));
    }
}
