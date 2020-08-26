package duke;

import duke.task.Task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    TaskList tasks;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        tasks = new TaskList();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void addTask_emptyList_success() {

        String actualResult = new TaskList().addTask(Task.TaskType.TODOS, "CS2103T Exams", "2020-12-31 2021");

        String expectedResult = "Got it, here yur task bij\n" +
                "[T][✘] CS2103T Exams (by: 2020-12-31 2021)\n" +
                "Now you have 1 tasks in the list.";

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteTask_singleList_success() {

        TaskList tasks = new TaskList();
        tasks.addTask(Task.TaskType.TODOS, "CS2103T Exams", "2020-12-31 2021");
        String actualResult = tasks.removeTask(0);

        String expectedResult = "[T][✘] CS2103T Exams (by: 2020-12-31 2021)";

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getSize_emptyList_success() {

        TaskList tasks = new TaskList();
//        tasks.addTask(Task.TaskType.TODOS, "CS2103T Exams", "2020-12-31 2021");
        int actualResult = tasks.getSize();
        int expectedResult = 0;

        assertEquals(expectedResult, actualResult);
    }
}
