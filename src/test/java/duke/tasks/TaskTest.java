package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskTest {

    @Test
    public void taskHasLocalDate_hasLocalDate_returnsTrue() {
        LocalDate correctFormat = LocalDate.parse("2020-06-05");
        Task testTask = new Task("test1", true, correctFormat);
        assertEquals(true, testTask.hasDate());
    }

    @Test
    public void taskHasLocalDate_noLocalDate_returnsFalse() {
        Task testTask = new Task("test1");
        assertEquals(false, testTask.hasDate());
    }

    @Test
    public void taskGetDate_hasLocalDate_returnsDate() {
        LocalDate correctFormat = LocalDate.parse("2020-06-05");
        Task testTask = new Task("test1", true, correctFormat);
        assertEquals(correctFormat, testTask.getDate());
    }

    @Test
    public void taskGetDate_noLocalDate_returnsNull() {
        Task testTask = new Task("test1");
        assertEquals(null, testTask.getDate());
    }

    @Test
    public void taskToString_DoneTask_tickPrinted() {
        String boxWithTick = "[" + "\u2713" + "] ";
        Task testTask = new Task("test1");
        testTask.setDone();
        assertEquals(boxWithTick + "test1", testTask.toString());
    }

    @Test
    public void taskToString_notDoneTask_crossPrinted() {
        String boxWithCross = "[" + "\u2718" + "] ";
        Task testTask = new Task("test1");
        assertEquals(boxWithCross + "test1", testTask.toString());
    }

    @Test
    public void taskSaveFormat_DoneTask_onePrinted() {
        String expectedOutput = "1 | test1";
        Task testTask = new Task("test1");
        testTask.setDone();
        assertEquals(expectedOutput, testTask.getSaveFormat());
    }

    @Test
    public void taskSaveFormat_notDoneTask_zeroPrinted() {
        String expectedOutput = "0 | test1";
        Task testTask = new Task("test1");
        assertEquals(expectedOutput, testTask.getSaveFormat());
    }

}
