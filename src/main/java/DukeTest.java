import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void Deadline_Wrong_Format1_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () ->
            Deadline.create("deadline read book 2019/12/02 18:00"));

        String expected = "Please include '/by' in front of the deadline";
        Assertions.assertEquals(expected,e.getMessage());
    }

    @Test
    public void Deadline_Wrong_Format2_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () ->
            Deadline.create("deadline read book /by 02/12/2019 18:00"));

        String expected = "please enter a valid yyyy-mm-dd format";
        Assertions.assertEquals(expected,e.getMessage());
    }

    @Test
    public void Deadline_Wrong_Format3_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () ->
            Deadline.create("deadline read book /by 2019/12/02 1800"));

        String expected = "please enter a valid HH:MM format";
        Assertions.assertEquals(expected,e.getMessage());
    }

    @Test
    public void Deadline_Wrong_Format4_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () ->
            Deadline.create("deadline read book /at 2019/12/02 18:00"));

        String expected = "Please include '/by' in front of the deadline";
        Assertions.assertEquals(expected,e.getMessage());
    }

    @Test
    public void Event_Wrong_Format1_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () ->
            Event.create("event read book 2019/12/02 18:00"));

        String expected = "Please include '/at' in front of the event time period";
        Assertions.assertEquals(expected,e.getMessage());
    }

    @Test
    public void Event_Wrong_Format2_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () ->
            Event.create("event read book /at 02/12/2019 18:00"));

        String expected = "please enter a valid yyyy-mm-dd format";
        Assertions.assertEquals(expected,e.getMessage());
    }

    @Test
    public void Event_Wrong_Format3_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () ->
            Event.create("event read book /at 2019/12/02 1800"));

        String expected = "please enter a valid HH:MM format";
        Assertions.assertEquals(expected,e.getMessage());
    }

    @Test
    public void Event_Wrong_Format4_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () ->
            Event.create("event read book /by 2019/12/02 1800"));

        String expected = "Please include '/at' in front of the event time period";
        Assertions.assertEquals(expected,e.getMessage());
    }

}
