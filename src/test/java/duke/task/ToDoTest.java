package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void constructor_oneParam_success() {
        ToDo newTodo = new ToDo("workout");
        assertFalse(newTodo.isDone());
        assertEquals("[T][\u2718] workout", newTodo.toString());

    }

    @Test
    public void constructor_fullParams_success() {
        ToDo newTodo = new ToDo("workout", true, "-", LocalDateTime.now());
        assertTrue(newTodo.isDone());
        assertEquals("[T][\u2713] workout", newTodo.toString());
    }

    @Test
    public void getTimeFrame_defaultTimeFrame_success() {
        ToDo toDo = new ToDo("test");
        assertEquals("-", toDo.getTimeFrame());
    }

    @Test
    public void getTime_defaultTime_success() {
        ToDo toDo = new ToDo("test");
        assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("d MMM yyyy @ h.mm a")), toDo.getTime());
    }

}
