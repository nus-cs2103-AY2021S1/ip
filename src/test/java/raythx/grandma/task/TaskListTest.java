package raythx.grandma.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private TaskList tasks;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        tasks = new TaskList();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    /**
     * Something.
     */
    @Test
    public void addTask_emptyList_success() {

//        String actualResult = new TaskList().addTask("T", "CS2103T Exams", "2020-12-31 2021");
//
//        String expectedResult = "Got it, here yur task bij\n"
//                + "[T][✘] CS2103T Exams (by: Dec 31 2020, 8:21PM)\n"
//                + "Now you have 1 tasks in the list.";
//
//        assertEquals(expectedResult, actualResult);
    }

    /**
     * Something.
     */
    @Test
    public void deleteTask_singleList_success() {

//        TaskList tasks = new TaskList();
//        tasks.addTask(Task.TaskType.TODOS, "CS2103T Exams", "2020-12-31 2021");
//        String actualResult = tasks.removeTask(0);
//
//        String expectedResult = "[T][✘] CS2103T Exams (by: Dec 31 2020, 8:21PM)";
//
//        assertEquals(expectedResult, actualResult);
    }

    /**
     * Something.
     */
    @Test
    public void getSize_emptyList_success() {

        TaskList tasks = new TaskList();
        int actualResult = tasks.getSize();
        int expectedResult = 0;

        assertEquals(expectedResult, actualResult);
    }
}
