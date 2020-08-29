package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {

    // to test correctness of task output
    @Test
    public void TodoTest() {
        ToDo todo = new ToDo("return book");
        Assertions.assertEquals(todo.toString(), "[T][\u2718] return book");
    }

    @Test
    public void deadlineTest() {
        Deadline deadline = null;
        try {
            deadline = new Deadline("return book", "2020-08-22");
            Assertions.assertEquals(deadline.toString(), "[D][\u2718] return book (by: Aug 22 2020)");
        } catch (DukeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void eventTest() {
        Event event = null;
        try {
            event = new Event("return book", "2020-08-22 14:00-16:00");
            Assertions.assertEquals(event.toString(), "[E][\u2718] return book (at: Aug 22 2020 14:00 - 16:00)");
        } catch (DukeException e) {
            e.printStackTrace();
        }

    }
}
