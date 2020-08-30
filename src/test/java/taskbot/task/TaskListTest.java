package taskbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;



/**
 * Tests the methods of the TaskList class.
 */
public class TaskListTest {
    private TaskList taskList;
    private ArrayList<Task> tasks;

    public TaskListTest() {
        tasks = new ArrayList<>();
        // Adds task stubs
        for (int i = 1; i < 4; i++) {
            tasks.add(new TaskStub("Task " + i));
        }
        taskList = new TaskList(tasks);
    }

    @Test
    public void testListTasks() {
        try {
            StringBuilder expected = new StringBuilder("These are the following task(s) to complete:\n");
            for (int i = 1; i < 3; i++) {
                expected.append(i).append(". ").append("[✗] Task ").append(i).append("\n");
            }
            expected.append("3. [✗] Task 3");
            assertEquals(expected.toString(), taskList.listTasks());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testListTasksSize() {
        String expected = "You now have 3 task(s) in the list.";
        assertEquals(expected, taskList.listTaskSize());
    }

    @Test
    public void testAddTodo() {
        try {
            String task = "TodoTask 1";
            Todo todoTask = new Todo(task);
            String expected = "I have added a Todo:\n" + todoTask
                    + "\n" + "You now have 4 task(s) in the list.";

            assertEquals(expected, taskList.addTodoTask(task));

            // Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testAddEvent() {
        try {
            String task = "EventTask /at 2020-08-03 0845";
            Event eventTask = new Event("EventTask ", LocalDateTime.parse("2020-08-03T08:45:00"));
            String expected = "I have added an Event:\n" + eventTask
                    + "\n" + "You now have 4 task(s) in the list.";

            assertEquals(expected, taskList.addEventTask(task));

            // Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testAddDeadline() {
        try {
            String task = "DeadlineTask /by 2020-08-03 0845";
            Deadline deadlineTask = new Deadline("DeadlineTask ", LocalDateTime.parse("2020-08-03T08:45:00"));
            String expected = "I have added a Deadline:\n" + deadlineTask
                    + "\n" + "You now have 4 task(s) in the list.";

            assertEquals(expected, taskList.addDeadlineTask(task));

            // Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCompleteTask() {
        try {
            String expected = "Understood. The following task is now marked as done:\n"
                    + "    [✓] Task 3";
            assertEquals(expected, taskList.completeTask(2));

            // Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDeleteTask() {
        try {
            String expected = "Understood. The following task has been deleted.\n"
                    + "    [✗] Task 3\n"
                    + "You now have 2 task(s) in the list.";
            assertEquals(expected, taskList.deleteTask(2));

            // Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetUpcoming() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            LocalDateTime currentDateTime = LocalDateTime.now();
            // Task without any time
            tasks.add(new TaskStub("Task 1"));
            // Current time
            tasks.add(new TimedTaskStub("Timed Task 1", currentDateTime));
            // 1 Day later
            tasks.add(new TimedTaskStub("Timed Task 2", currentDateTime.plusDays(1)));
            // 7 Days later
            tasks.add(new TimedTaskStub("Timed Task 3", currentDateTime.plusDays(7)));

            taskList = new TaskList(tasks);

            String expected = "These are the following task(s) to complete:\n"
                    + "1. [✗] Task 1\n"
                    + "2. [✗] Timed Task(test): Timed Task 1(Time: "
                    + currentDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")\n"
                    + "3. [✗] Timed Task(test): Timed Task 2(Time: "
                    + currentDateTime.plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"))
                    + ")\n";

            assertEquals(expected, taskList.getUpcoming(3));

            // Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFindTasks() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            tasks.add(new TaskStub("Run"));
            tasks.add(new TaskStub("run"));
            tasks.add(new TaskStub("run away"));
            tasks.add(new TaskStub("not printed"));

            taskList = new TaskList(tasks);

            String[] keywords = new String[]{"run"};

            String expected = "Here are the matching tasks in your list:\n"
                    + "1. [✗] Run\n"
                    + "2. [✗] run\n"
                    + "3. [✗] run away\n";
            assertEquals(expected, taskList.findTasks(keywords));

            //Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

    @SuppressWarnings("checkstyle:SingleSpaceSeparator")
    @Test
    public void testFindTasksMultipleKeywords() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            tasks.add(new TaskStub("walk"));
            tasks.add(new TaskStub("run"));
            tasks.add(new TaskStub("run away"));
            tasks.add(new TaskStub("not printed"));

            taskList = new TaskList(tasks);

            String[] keywords = new String[]{"run", "walk"};

            String expected = "Here are the matching tasks in your list:\n"
                    + "1. [✗] walk\n"
                    + "2. [✗] run\n"
                    + "3. [✗] run away\n";
            assertEquals(expected, taskList.findTasks(keywords));

            //Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }
}
