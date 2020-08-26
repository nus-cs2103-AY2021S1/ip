import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void string() {
        Todo todo = new Todo("todo");
        Deadline deadline = new Deadline("deadline", "2020-08-25");
        Event event = new Event("event", "2020-08-25");
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        tasks.addTask(todo, ui);
        tasks.addTask(deadline, ui);
        tasks.addTask(event, ui);

        String expected = "[T][\u2718] todo\n"
                + "[D][\u2718] deadline - 2020-08-25\n"
                + "[E][\u2718] event - 2020-08-25\n";

        assertEquals(tasks.toString(), expected);
    }
}
