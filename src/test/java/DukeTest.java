import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;


public class DukeTest {
    Task toDo = new ToDo("Read Book");
    Task event = new Event("Project Meeting", "today");
    Task deadline = new Deadline("Return Book", "2020-09-01");

    @Test
    public void ToDoTest(){
        assertEquals("Read Book", toDo.getContent());
    }

    @Test
    public void EventTest() {
        assertEquals("today", event.getDate());
    }

    @Test
    public void DeadlineTest() {
        Assertions.assertFalse("Sep 1, 2020" == deadline.getDate());
        Assertions.assertTrue(deadline.getDate().equals("2020-09-01"));
    }
}