package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void testStringPrinting1(){
        String expected = "[\u2713]testing.";
        Task testTask = new Task("testing.", LocalDateTime.now(), true);
        assertEquals(testTask.toString(),expected);
    }

    @Test
    public void testStringPrinting2(){
        String expected = "[T][\u2713]testing.";
        Task testTask = new Todo("testing.", true);
        assertEquals(testTask.toString(),expected);
    }

    @Test
    public void testStringPrinting3(){
        String expected = "[E][\u2713]testing. (at : 02 Jan 2020 1605)";
        Task testTask = new Event("testing.", LocalDateTime.of(2020,01,02,16,05), true);
        assertEquals(expected, testTask.toString());
    }

    @Test
    public void testDueOn1(){
        Task testTask = new Event("testing.", LocalDateTime.of(2020,01,02,16,05), true);
        assertTrue(testTask.isDueOn(LocalDate.of(2020,01,02)));
    }

    @Test
    public void testDueOn2(){
        Task testTask = new Deadline("testing.", LocalDateTime.of(2020,01,02,16,05), true);
        assertFalse(testTask.isDueOn(LocalDate.of(2020,01,01)));
    }

    @Test
    public void testIsDone1(){
        Task testTask = new Event("testing.", LocalDateTime.of(2020,01,02,16,05), true);
        assertTrue(testTask.isDone());
    }

    @Test
    public void testIsDone2(){
        Task testTask = new Deadline("testing.", LocalDateTime.of(2020,01,02,16,05), false);
        assertFalse(testTask.isDone());
    }
}
