package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ToDoTest {

    @Test
    public void taskToString_DoneTask_tickPrinted() {
        String boxWithTick = "[T][" + "\u2713" + "] ";
        Task testTodo = new ToDo("test1");
        testTodo.setDone();
        assertEquals(boxWithTick + "test1", testTodo.toString());
    }

    @Test
    public void taskToString_notDoneTask_crossPrinted() {
        String boxWithCross = "[T][" + "\u2718" + "] ";
        Task testTodo = new ToDo("test1");
        assertEquals(boxWithCross + "test1", testTodo.toString());
    }

    @Test
    public void taskSaveFormat_DoneTask_onePrinted() {
        String expectedOutput = "T | 1 | test1";
        Task testTodo = new ToDo("test1");
        testTodo.setDone();
        assertEquals(expectedOutput, testTodo.getSaveFormat());
    }

    @Test
    public void taskSaveFormat_notDoneTask_zeroPrinted() {
        String expectedOutput = "T | 0 | test1";
        Task testTodo = new ToDo("test1");
        assertEquals(expectedOutput, testTodo.getSaveFormat());
    }
}
