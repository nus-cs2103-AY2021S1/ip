package duke.components;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void eventTest() throws DukeException {

        Event lunchEvent = new Event("event lunch with family", LocalDate.parse("2020-09-10"),"1300");
        assertEquals("[E][\u2718] lunch with family (at: Sep 10 2020 1300)", lunchEvent.toString());

    }
    @Test
    public void deadlineTest() throws DukeException {

        Deadline homeworkDeadline = new Deadline("deadline CS2103T homework", LocalDate.parse("2020-09-10"));
        assertEquals("[D][\u2718] CS2103T homework (by: Sep 10 2020)", homeworkDeadline.toString());

    }
    @Test
    public void todoTest() throws DukeException {

        ToDo homeworkDeadline = new ToDo("todo read a book");
        assertEquals("[T][\u2718] read a book", homeworkDeadline.toString());

    }
}

