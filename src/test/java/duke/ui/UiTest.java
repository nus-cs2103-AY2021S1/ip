package duke.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
public class UiTest {
    private static final String CROSS_ICON = "\u2718";

    private Task[] tasks = {
            new ToDo("read book"),
            new Deadline("submit project", "20/4/2019"),
            new Event("shoppee", "12/4/2019 to 02/9/2019")
    };
    private ArrayList<Task> testSample = new ArrayList<>(Arrays.asList(tasks));

    // @author aidoxe-123
    // As reference from https://github.com/nus-cs2103-AY2021S1/forum/issues/65
    // Reused from https://www.baeldung.com/java-testing-system-out-println with minor modifications
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    //@@author

    @Test
    public void showTasks_threeTypesOfTasks_success() {
        String actual = new Ui().showTasks(new TaskList(testSample));

        String expected = "Here are the task(s) in your list:\n"
                + "1. [T] [" + CROSS_ICON + "] read book\n"
                + "2. [D] [" + CROSS_ICON + "] submit project (by: 20 Apr 2019)\n"
                + "3. [E] [" + CROSS_ICON + "] shoppee (at: from 12 Apr 2019 to 2 Sep 2019)";

        //assertEquals(expected.replaceAll("\\p{Cntrl}", " "),
        //        actual.replaceAll("\\p{Cntrl}", " "));
        Assertions.assertEquals(expected, actual);
    }
}
