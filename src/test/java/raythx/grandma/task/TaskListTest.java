package raythx.grandma.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import raythx.grandma.exception.DukeException;

public class TaskListTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Task task;

    //@@author raythx98-reused
    //Reused from https://www.baeldung.com/java-testing-system-out-println with minor modifications
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        try {
            task = new ToDo("Something", "Cute", "311220 2029");
        } catch (DukeException exception) {
            exception.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    //@@author

    /**
     * Something.
     */
    @Test
    public void addTask_emptyList_createdCorrectly() {
        TaskList tasks = new TaskList();
        String expectedResult = task.toString();
        tasks.addTask(task);
        String actualResult = tasks.getTask(0).toString();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Something.
     */
    @Test
    public void deleteTask_singleList_deletedCorrectly() {

        TaskList tasks = new TaskList();
        tasks.addTask(task);
        String actualResult = tasks.removeTask(0);
        String expectedResult = task.toString();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Something.
     */
    @Test
    public void getSize_emptyList_retrievedCorrectly() {
        TaskList tasks = new TaskList();
        int actualResult = tasks.getSize();
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }
}
