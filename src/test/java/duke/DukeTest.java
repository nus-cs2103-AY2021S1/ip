package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.Task;

public class DukeTest {
    @Test
    void list_emptyList_emptyListText() {
        Storage s = new StorageStub();
        Duke d = new Duke(s);
        assertEquals(d.getResponse("list"), "No task added yet!");
    }

    @Test
    void todo_validDescription_success() {
        Storage s = new StorageStub();
        Duke d = new Duke(s);
        String actual = d.getResponse("todo testTask");
        Task task = d.getTasks().getTask(0);
        String expected = "Got it. I've added this task:\n  " + task.toString() + "\nNow you have "
                + d.getTasks().size() + " tasks in the list.";
        assertEquals(actual, expected);

    }

    @Test
    void event_validDescription_success() {
        Storage s = new StorageStub();
        Duke d = new Duke(s);
        String actual = d.getResponse("event testTask /at 2020-04-23 2355");
        Task task = d.getTasks().getTask(0);
        String expected = "Got it. I've added this task:\n  " + task.toString() + "\nNow you have "
                + d.getTasks().size() + " tasks in the list.";
        assertEquals(actual, expected);

    }

    @Test
    void deadline_validDescription_success() {
        Storage s = new StorageStub();
        Duke d = new Duke(s);
        String actual = d.getResponse("deadline testTask /by 2020-04-23 2355");
        Task task = d.getTasks().getTask(0);
        String expected = "Got it. I've added this task:\n  " + task.toString() + "\nNow you have "
                + d.getTasks().size() + " tasks in the list.";
        assertEquals(actual, expected);

    }

    @Test
    void fdtask_validDescription_success() {
        Storage s = new StorageStub();
        Duke d = new Duke(s);
        String actual = d.getResponse("fdtask testTask /for 2 hours");
        Task task = d.getTasks().getTask(0);
        String expected = "Got it. I've added this task:\n  " + task.toString() + "\nNow you have "
                + d.getTasks().size() + " tasks in the list.";
        assertEquals(actual, expected);

    }

    @Test
    void delete_validIndex_success() {
        Storage s = new StorageStub();
        Duke d = new Duke(s);
        d.getResponse("fdtask testTask /for 2 hours");
        String actual = d.getResponse("delete 1");
        assertTrue(actual.matches(
                "Noted\\. I've removed this task:\n  \\[.*\nNow you have \\d tasks in the list."));
    }

    @Test
    void find_validIndex_success() {
        Storage s = new StorageStub();
        Duke d = new Duke(s);
        d.getResponse("fdtask testTask /for 2 hours");
        String actual = d.getResponse("find test");
        assertTrue(actual.matches("Here are the matching tasks in your list:\n.+"));
    }

    @Test
    void done_validIndex_success() {
        Storage s = new StorageStub();
        Duke d = new Duke(s);
        d.getResponse("fdtask testTask /for 2 hours");
        String actual = d.getResponse("done 1");
        assertTrue(actual.matches("Nice! I've marked this task as done:\n\\[.+]\\[✓].+"));
    }
}
