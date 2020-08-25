import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {
    @Test
    public void listTasks_listOfTasks_success() {
        try {
            TaskManager tm = new TaskManager();
            tm.add(new Todo("eat"));
            tm.add(new Deadline("assignment", "24 5 2020"));
            String expected = "Here are your tasks\n" +
                    "\n1. [T][x] eat" +
                    "\n2. [D][x] assignment (due: 24/05/2020)";
            assertEquals(expected, tm.listTasks());
        } catch (DukeException e) {
            assertEquals("OOPS! Date format is invalid!", e.getMessage());
        }

    }

    @Test
    public void markDone_todoMarkedDone_success() {
        try {
            TaskManager tm = new TaskManager();
            tm.add(new TodoStub("eat"));
            Task task = tm.markDone(1);
            assertEquals("[T][\u2713] eat", task.toString());
        } catch (DukeException e) {
            assertEquals("OOPS! you gave an invalid task number!", e.getMessage());
        }

    }
}
