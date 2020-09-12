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
            StringBuilder expectedOutput = new StringBuilder("These are the following task(s) to complete:\n");
            for (int i = 1; i < 3; i++) {
                expectedOutput.append(i).append(". ").append("[\u2718] Task ").append(i).append("\n");
            }
            expectedOutput.append("3. [\u2718] Task 3");
            assertEquals(expectedOutput.toString(), taskList.listTasks());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testListTasksSize() {
        String expectedOutput = "You now have 3 task(s) in the list.";
        assertEquals(expectedOutput, taskList.getNumberOfTasks());
    }

    @Test
    public void testAddTodo() {
        try {
            String task = "TodoTask 1";
            Todo todoTask = new Todo(task);
            String expectedOutput = "I have added a Todo:\n" + todoTask
                    + "\n" + "You now have 4 task(s) in the list.";

            assertEquals(expectedOutput, taskList.addTodoTask(task));

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
            String expectedOutput = "I have added an Event:\n" + eventTask
                    + "\n" + "You now have 4 task(s) in the list.";

            assertEquals(expectedOutput, taskList.addEventTask(task));

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
            String expectedOutput = "I have added a Deadline:\n" + deadlineTask
                    + "\n" + "You now have 4 task(s) in the list.";

            assertEquals(expectedOutput, taskList.addDeadlineTask(task));

            // Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCompleteTask() {
        try {
            String expectedOutput = "Understood. The following task is now marked as done:\n"
                    + "    [\u2713] Task 3";
            assertEquals(expectedOutput, taskList.completeTask(2));

            // Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDeleteTask() {
        try {
            String expectedOutput = "Understood. The following task has been deleted.\n"
                    + "    [\u2718] Task 3\n"
                    + "You now have 2 task(s) in the list.";
            assertEquals(expectedOutput, taskList.deleteTask(2));

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

            String expectedOutput = "These are the following task(s) to complete:\n"
                    + "1. [\u2718] Task 1\n"
                    + "2. [\u2718] Timed Task(test): Timed Task 1(Time: "
                    + currentDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")\n"
                    + "3. [\u2718] Timed Task(test): Timed Task 2(Time: "
                    + currentDateTime.plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"))
                    + ")\n";

            assertEquals(expectedOutput, taskList.getUpcoming(3));

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

            String expectedOutput = "Here are the matching tasks in your list:\n"
                    + "1. [\u2718] Run\n"
                    + "2. [\u2718] run\n"
                    + "3. [\u2718] run away\n";
            assertEquals(expectedOutput, taskList.findTasks(keywords));

            //Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

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

            String expectedOutput = "Here are the matching tasks in your list:\n"
                    + "1. [\u2718] walk\n"
                    + "2. [\u2718] run\n"
                    + "3. [\u2718] run away\n";
            assertEquals(expectedOutput, taskList.findTasks(keywords));

            //Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }
}
