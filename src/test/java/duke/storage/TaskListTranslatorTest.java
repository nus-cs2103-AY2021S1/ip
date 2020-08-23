package duke.storage;

import duke.parsers.DukeDateTimeParser;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class TaskListTranslatorTest {

    private List<String> lines = Arrays.asList(
            "T | 0 | todo",
            "D | 0 | deadline | 23 Aug 2020 9:00 PM",
            "E | 1 | event | 23 Aug 2020"
    );

    @Test
    public void decode_NonEmptyInput_success() {

        TaskList taskList = TaskListTranslator.decode(lines);
        ArrayList<Task> tasks = taskList.getTasks();
        String[] expected = {
                "[T][\u2718] todo",
                "[D][\u2718] deadline (by: 23 Aug 2020 9:00 PM)",
                "[E][\u2713] event (at: 23 Aug 2020)"
        };
        for (int i = 0; i < 3; i++) {
            assertEquals(expected[i], tasks.get(i).toString());
        }
    }

    @Test
    public void decode_emptyInput_success() {
        TaskList taskList = TaskListTranslator.decode(new ArrayList<>());
        assertEquals(0, taskList.numOfTasks());
    }

    @Test
    public void testEncode() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        Deadline deadline = new Deadline("deadline",
                DukeDateTimeParser.parse("23/8/2020 9:00 PM"));
        Event event = new Event("event",
                DukeDateTimeParser.parse("23/8/2020"));
        event.markAsDone();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        assertEquals(lines, TaskListTranslator.encode(taskList));
    }
}
