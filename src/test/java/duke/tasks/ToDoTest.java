package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void taskToString_doneTask_tickPrinted() {
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
    public void taskSaveFormat_doneTask_onePrinted() {
        String expectedOutput = "T | 1 | 0 | test1";
        Task testTodo = new ToDo("test1");
        testTodo.setDone();
        assertEquals(expectedOutput, testTodo.getSaveFormat());
    }

    @Test
    public void taskSaveFormat_hasReminder_onePrinted() {
        String expectedOutput = "T | 0 | 1 | test1";
        Task testTodo = new ToDo("test1");
        testTodo.setReminder();
        assertEquals(expectedOutput, testTodo.getSaveFormat());
    }

    @Test
    public void taskSaveFormat_noReminder_zeroPrinted() {
        String expectedOutput = "T | 0 | 0 | test1";
        Task testTodo = new ToDo("test1");
        assertEquals(expectedOutput, testTodo.getSaveFormat());
    }

    @Test
    public void taskSaveFormat_notDoneTaskHasReminder_zeroOnePrinted() {
        String expectedOutput = "T | 0 | 1 | test1";
        Task testTodo = new ToDo("test1");
        testTodo.setReminder();
        assertEquals(expectedOutput, testTodo.getSaveFormat());
    }

    @Test
    public void taskSaveFormat_doneTaskHasReminder_oneOnePrinted() {
        String expectedOutput = "T | 1 | 1 | test1";
        Task testTodo = new ToDo("test1");
        testTodo.setDone();
        testTodo.setReminder();
        assertEquals(expectedOutput, testTodo.getSaveFormat());
    }

    @Test
    public void taskSaveFormat_notDoneTask_zeroPrinted() {
        String expectedOutput = "T | 0 | 0 | test1";
        Task testTodo = new ToDo("test1");
        assertEquals(expectedOutput, testTodo.getSaveFormat());
    }
}
