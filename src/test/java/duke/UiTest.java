package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    ToDo todo = new ToDo("homework");
    Deadline deadline = new Deadline("assignment", LocalDate.parse("2020-09-05"));
    Event event = new Event("party", LocalDate.parse("2020-09-04"));

    @Test
    public void showNumOfTasksMessage_numOfTasksShownSuccessfully() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        String actual = ui.showNumOfTasksMessage(tasks);
        String expected = "\nNow you have 3 task(s) in the list.";
        assertEquals(expected, actual);
    }
}
