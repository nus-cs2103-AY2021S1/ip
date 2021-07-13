package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class UiTest {
    private final ToDo todo = new ToDo("homework");
    private final Deadline deadline = new Deadline("assignment", LocalDate.parse("2020-09-05"));
    private final Event event = new Event("party", LocalDate.parse("2020-09-04"));

    @Test
    public void generateNumOfTasksMessage_numOfTasksgeneratedSuccessfully() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);
        String actual = ui.generateNumOfTasksMessage(tasks);
        String expected = "\nNow you have 3 task(s) in the list.";
        assertEquals(expected, actual);
    }
}
