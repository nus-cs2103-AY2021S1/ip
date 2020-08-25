package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testTaskListAdd() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("clean my room"));
        tasks.addTask(new Deadline("do CS2103", "2020-05-25"));
        tasks.addTask(new Event("meet CS2103 group", "2020-05-25 12:34 13:34"));
        String taskList = tasks.printList();
        String expectedList = "Here are the tasks in your list:\n"
        + "1. [T][✘] clean my room\n"
        + "2. [D][✘] do CS2103 (by: May 25 2020)\n"
        + "3. [E][✘] meet CS2103 group (at: May 25 2020 12:34 to May 25 2020 13:34)";
        assertEquals(taskList, expectedList);
    }

    @Test
    public void testTaskListDone() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("clean my room"));
        tasks.addTask(new Deadline("do CS2103", "2020-05-25"));
        tasks.addTask(new Event("meet CS2103 group", "2020-05-25 12:34 13:34"));
        tasks.markTaskAsDone(1);
        String taskList = tasks.printList();
        String expectedList = "Here are the tasks in your list:\n"
        + "1. [T][✓] clean my room\n"
        + "2. [D][✘] do CS2103 (by: May 25 2020)\n"
        + "3. [E][✘] meet CS2103 group (at: May 25 2020 12:34 to May 25 2020 13:34)";
        assertEquals(taskList, expectedList);
    }

    @Test
    public void testTaskListDelete() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("clean my room"));
        tasks.addTask(new Deadline("do CS2103", "2020-05-25"));
        tasks.addTask(new Event("meet CS2103 group", "2020-05-25 12:34 13:34"));
        tasks.deleteTask(2);
        String taskList = tasks.printList();
        String expectedList = "Here are the tasks in your list:\n"
                + "1. [T][✘] clean my room\n"
                + "2. [E][✘] meet CS2103 group (at: May 25 2020 12:34 to May 25 2020 13:34)";
        assertEquals(taskList, expectedList);
    }
}
