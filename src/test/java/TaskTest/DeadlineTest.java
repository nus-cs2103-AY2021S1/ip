package TaskTest;

import Exceptions.DukeException;
import ParserStorageUi.Storage;
import Task.Deadline;
import Task.Task;
import Task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void setToTrueTest() throws DukeException {
        assertEquals(new Deadline("cs2103 project", false, "2020-06-26 2359").setToTrue(), new Deadline("cs2103 project", true, "2020-06-26 2359"));
    }

    @Test
    public void getTypeTest() throws DukeException {
        assertEquals(new Deadline("cs2103 project", false, "2020-06-26 2359").getType(), new Deadline("cs2103 project", true, "2020-06-26 2359").getType());
    }

    @Test
    public void getEndTest() throws DukeException {
        assertEquals(new Deadline("cs2103 project", false, "2020-06-26 2359").getEnd(), new Deadline("cs2103 project", true, "2020-06-26 2359").getEnd());
    }

}
