package duke.task;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void createTask_dateOnly_Success() {
        Deadline deadline = Deadline.createTask("return book /by 20-03-2019");
        assertEquals("[D][✘] return book (by: 20 Mar 2019)", deadline.toString());
    }

    @Test
    public void createTask_dateTime_Success() {
        Deadline deadline = Deadline.createTask("IP Project A-JUnit /by 20-03-2019 23:59");
        assertEquals("[D][✘] IP Project A-JUnit (by: 20 Mar 2019 23:59)", deadline.toString());
    }

    @Test
    public void createTask_unrecognisedDate_Failure() {
        DukeException thrown = assertThrows(DukeException.class, () -> {
            Deadline deadline = Deadline.createTask("Fix parsing /by 20th January 2019");
        });
        assertTrue(thrown.getMessage().contains("I'm not quite sure if we know each other..."));
    }

    @Test
    public void createTask_unrecognisedTime_Failure() {
        DukeException thrown = assertThrows(DukeException.class, () -> {
            Deadline deadline = Deadline.createTask("Fix parsing /by 20th January 2019");
        });
        assertTrue(thrown.getMessage().contains("I'm not quite sure if we know each other..."));
    }

    @Test
    public void createTask_withoutDate_Failure() {
        DukeException thrown = assertThrows(DukeException.class, () -> {
            Deadline deadline = Deadline.createTask("Submit homework");
        });
        assertTrue(thrown.getMessage().contains("So you never did plan on doing it huh..."));
    }

    @Test
    public void createTask_withoutDescription_Failure() {
        DukeException thrown = assertThrows(DukeException.class, () -> {
            Deadline deadline = Deadline.createTask(null);
        });
        assertTrue(thrown.getMessage().contains("I need something to work with."));
    }

    @Test
    public void encode_decode_Success() {
        Deadline deadline = Deadline.createTask("Description /by 20-12-2020 12:00");
        assertNotNull(deadline.encode());
        assertTrue(Deadline.decode(deadline.encode()).toString().equals(deadline.toString()));
    }
}
