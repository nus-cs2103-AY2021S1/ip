package butler.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void showTaskDeletedTest() {
        Ui ui = new Ui();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String expectedOutput = "Task 1 has been deleted." + System.lineSeparator();
        ui.showTaskIsDeleted(1);
        assertEquals(expectedOutput, outContent.toString());
    }
}
