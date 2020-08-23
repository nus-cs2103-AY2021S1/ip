import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;


public class DukeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public static TaskList shelf;
    public static Storage storage;
    public static Parser parser;
    public static UI ui;
    public static DateTimeFormatter formatter;

    @BeforeAll
    public static void setUp() {
        File f = new File("D:\\24092014\\Joven\\UNI STUFF\\CS2103\\IP\\task.txt");
        storage = new Storage(f);
        shelf = new TaskList(storage.loadFile());
        ui = new UI(shelf, storage);
        parser = new Parser(storage, shelf, ui);
        formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testBye() {
        ui.replyBye();
        assertEquals("CYA PAL. Hope to see you again!\r\n", outContent.toString());
    }

    @Test
    public void testAddDeadLine_deadlineInput_success() {
        try {
            ui.addDeadline("finish homework!", "2020-10-02 11:44");
            String currentTime = LocalDateTime.now().format(formatter);
            assertEquals("Got it. I've added this task: \r\n  [D][✘] finish homework! [created on " +
                            currentTime +
                            "] (by: Oct 2 2020 11:44)\r\nNow you have 1 tasks in the list.\r\n",
                    outContent.toString());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testAddDeadLine_emptyDescription_exceptionThrow() {
        parser.listen("deadline ");
        assertEquals("☹ OOPS!!! The description of a deadline cannot be empty.\r\n",
                outContent.toString());
    }

    @Test
    public void testDelete() {
        try {
            ui.replyDelete(0);
            assertEquals("Noted. I've removed this task: \r\nNow you have 0 in the list.\r\n",
                    outContent.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
