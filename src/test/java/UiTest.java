import main.java.com.jacob.duke.Ui;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    //whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
    @Test
    public void showDone_omo_success() {
        new Ui().showDone("Omo");
        assertEquals("Nice! I've marked this task as done: \nOmo", outContent.toString().trim());
    }
    @Test
    public void sayBye_null_success() {
        new Ui().sayBye();
        assertEquals("Bye. Hope to see you again soon!", outContent.toString().trim());
    }
    @Test
    public void showDone_kek_success() {
        new Ui().showDone("Kek");
        assertEquals("Nice! I've marked this task as done: \nKek", outContent.toString().trim());
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

}
