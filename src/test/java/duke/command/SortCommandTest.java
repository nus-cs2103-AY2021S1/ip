package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

class SortCommandTest extends CommandTests {

    @Test
    public void execute_sort_success() {
        try {
            ToDo toDo = new ToDo("test1");
            Event event = new Event("newEvent", true, "2-4pm", LocalDateTime.now());
            LocalDateTime newTime1 = LocalDateTime.parse("13-8-20 1420", DateTimeFormatter.ofPattern("d-M-yy HHmm"));
            LocalDateTime newTime2 = LocalDateTime.parse("13-9-20 1420", DateTimeFormatter.ofPattern("d-M-yy HHmm"));
            Deadline earlierDeadline = new Deadline("earlierDeadline", newTime1);
            Deadline laterDeadline = new Deadline("laterDeadline", newTime2);

            taskList.add(toDo);
            taskList.add(event);
            taskList.add(laterDeadline);
            taskList.add(earlierDeadline);

            SortCommand sortCommand = new SortCommand();
            executeTask(sortCommand);

            // Sort should be in the order of earlierDeadline, laterDeadline, toDo, event.
            ArrayList<Task> sortedTaskList = storage.getTasks();
            assertEquals(earlierDeadline, sortedTaskList.get(0));
            assertEquals(laterDeadline, sortedTaskList.get(1));
            assertEquals(toDo, sortedTaskList.get(2));
            assertEquals(event, sortedTaskList.get(3));

        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
