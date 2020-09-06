import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.ArrayList;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void uiPrintMessage_customMessage_success() {
        String expected = "Hi there. This is a custom message.";
        Ui ui = new Ui("Hi there. This is a custom message.");
        ui.printMessage();
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void uiPrintTasks_customTaskList_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            String expected = "____________________________________________________\n\n" +
                    "Here are the tasks in your list:\n" +
                    "1.[T][✘] CS2103 iP\n" +
                    "2.[D][✘] NOC Application (by: Aug 30 2020)\n" +
                    "3.[E][✘] Suite Dinner (at: Sep 1 2020)\n\n" +
                    "____________________________________________________";

            tasks.add(new ToDo("CS2103 iP"));
            tasks.add(new Deadline("NOC Application", "2020-08-30"));
            tasks.add(new Event("Suite Dinner", "2020-09-01"));
            Ui ui = new Ui();
            ui.printTasks(tasks);

            assertEquals(expected, outputStreamCaptor.toString().trim());
        } catch (DukeException e) {
            System.out.println("Error occurred while testing");
        }
    }
}
