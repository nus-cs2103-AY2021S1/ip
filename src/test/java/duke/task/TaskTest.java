package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Tests the Task class for its default values.
 */
public class TaskTest {

    @Test
    public void markAsDone_done_success() {
        ToDo toDo = new ToDo("test", false, "-", LocalDateTime.now());
        toDo.markAsDone();
        assertEquals("Done", toDo.getStatus());
    }

    @Test
    public void isDone_correct_success() {
        ToDo toDo = new ToDo("test");
        assertFalse(toDo.isDone());
        toDo.markAsDone();
        assertTrue(toDo.isDone());
    }

    @Test
    public void getDescription_correct_success() {
        ToDo toDo = new ToDo("test");
        assertEquals("test", toDo.getDescription());
    }

    @Test
    public void getTaskName_correct_success() {
        ToDo toDo = new ToDo("test");
        assertEquals("TODO", toDo.getTaskName());
    }

    @Test
    public void getStatus_notDone_success() {
        ToDo toDo = new ToDo("test");
        assertEquals("Not done", toDo.getStatus());
    }

    @Test
    public void getStatus_done_success() {
        ToDo toDo = new ToDo("test", true, "-", LocalDateTime.now());
        assertEquals("Done", toDo.getStatus());
    }

    @Test
    public void match_correct_success() {
        String description1 = "man";
        String description3 = "woman";
        String description4 = "mane";
        String toMatch = "man";
        assertTrue(new ToDo(description1).match(toMatch));
        assertTrue(new ToDo(description3).match(toMatch));
        assertTrue(new ToDo(description4).match(toMatch));
    }

    @Test
    public void match_incorrect_success() {
        String description1 = "mAn";
        String description2 = "Man";
        String description3 = "m an";
        String description4 = "nma";
        String toMatch = "man";
        assertFalse(new ToDo(description1).match(toMatch));
        assertFalse(new ToDo(description2).match(toMatch));
        assertFalse(new ToDo(description3).match(toMatch));
        assertFalse(new ToDo(description4).match(toMatch));
    }

    @Test
    public void compareTo_exactSame_success() {
        LocalDateTime currentTime = LocalDateTime.now();
        ToDo toDo = new ToDo("tas", true, "-", currentTime);
        ToDo toDo1 = new ToDo("tas", true, "-", currentTime);
        assertEquals(0, toDo.compareTo(toDo1));
    }

    @Test
    public void compareTo_differentDescription_success() {
        LocalDateTime currentTime = LocalDateTime.now();
        ToDo toDo = new ToDo("tas", true, "-", currentTime);
        ToDo toDo1 = new ToDo("nope", true, "-", currentTime);
        assertEquals(0, toDo.compareTo(toDo1));
    }

    @Test
    public void compareTo_differentTimeFrame_success() {
        LocalDateTime currentTime = LocalDateTime.now();
        Event event = new Event("tas", true, "2-4pm", currentTime);
        Event event2 = new Event("tas", true, "6-10pm", currentTime);
        assertEquals(0, event.compareTo(event2));
    }

    @Test
    public void compareTo_firstDoneSecondNotDone_success() {
        LocalDateTime currentTime = LocalDateTime.now();
        ToDo toDo = new ToDo("tas", true, "-", currentTime);
        ToDo toDo1 = new ToDo("nope", false, "-", currentTime);
        assertEquals(1, toDo.compareTo(toDo1));
    }

    @Test
    public void compareTo_firstNotDoneSecondDone_success() {
        LocalDateTime currentTime = LocalDateTime.now();
        ToDo toDo = new ToDo("tas", false, "-", currentTime);
        ToDo toDo1 = new ToDo("nope", true, "-", currentTime);
        assertEquals(-1, toDo.compareTo(toDo1));
    }

    @Test
    public void compareTo_time_success() {
        Deadline deadline = new Deadline("tas", LocalDateTime.now().minusDays(1));
        Deadline deadline1 = new Deadline("tas", LocalDateTime.now());
        assertEquals(-1, deadline.compareTo(deadline1));
    }

    @Test
    public void compareTo_taskType_success() {
        ToDo toDo = new ToDo("tas", true, "-", LocalDateTime.now().minusDays(10));
        Event event = new Event("tas", false, "-", LocalDateTime.now().plusDays(10));
        Deadline deadline = new Deadline("tas", LocalDateTime.now());
        assertEquals(1, toDo.compareTo(deadline));
        assertEquals(1, toDo.compareTo(event));
        assertEquals(-1, deadline.compareTo(event));
    }

    @Test
    public void equals_same_success() {

    }

}
