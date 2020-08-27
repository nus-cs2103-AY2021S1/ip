package Test;
import com.Duke.Tasks.Deadline;
import com.Duke.Tasks.Event;
import com.Duke.Tasks.Task;
import com.Duke.Tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void toDoTest() {
        try {
            ToDo task = new ToDo("Do 2103 IP", false);
            assertEquals(task.toString(), "[T][\u2718] Do 2103 IP");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void eventTest() {
        try {
            Event task = new Event("Dinner Party", LocalTime.parse("15:30"), LocalTime.parse("17:30"), false);
            assertEquals(task.toString(), "[E][\u2718] Dinner Party(at: 15:30 - 17:30)");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void deadlineTest() {
        try {
            Deadline task = new Deadline("Graduation", LocalDate.parse("2011-11-19"), false);
            assertEquals(task.toString(), "[D][\u2718] Graduation(by: Nov 19 2011)");
        } catch (Exception ignored) {
        }
    }
}

