package taskbot.task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private TaskList taskList;
    private ArrayList<Task> tasks;

    // Used to test console output
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public TaskListTest() {
        tasks = new ArrayList<>();
        // Adds task stubs
        for (int i = 1; i < 4; i++) {
            tasks.add(new TaskStub("Task " + i));
        }
        taskList = new TaskList(tasks);
    }

    // Helps to test System.out.println output
    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    // Restores the output stream to System.out
    @AfterEach
    public void restoreStream() {
        System.setOut(System.out);
    }

    @Test
    public void testListTasks() {
        try {
            StringBuilder expected = new StringBuilder("These are the following task(s) to complete:\n");
            for (int i = 1; i < 3; i++) {
                expected.append(i).append(". ").append("Task ").append(i).append("\n");
            }
            expected.append("3. Task 3");
            taskList.listTasks();
            assertEquals(expected.toString(), outContent.toString());
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
            String expected = "I have added a Todo:\n" + todoTask +
                    "\n" + "You now have 4 task(s) in the list.\r\n";

            taskList.addTodoTask(task);
            assertEquals(expected, outContent.toString());

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
            String expected = "I have added an Event:\n" + eventTask +
                    "\n" + "You now have 4 task(s) in the list.\r\n";

            taskList.addEventTask(task);
            assertEquals(expected, outContent.toString());

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
            String expected = "I have added a Deadline:\n" + deadlineTask +
                    "\n" + "You now have 4 task(s) in the list.\r\n";

            taskList.addDeadlineTask(task);
            assertEquals(expected, outContent.toString());

            // Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCompleteTask() {
        try {
            taskList.completeTask(2);
            String expected = "Understood. The following task is now marked as done:\n" +
                    "    Task 3\r\n";
            assertEquals(expected, outContent.toString());

            // Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDeleteTask() {
        try {
            taskList.deleteTask(2);
            String expected = "Understood. The following task has been deleted.\n" +
                    "    Task 3\n" +
                    "You now have 2 task(s) in the list.\r\n";
            assertEquals(expected, outContent.toString());

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
            taskList.getUpcoming(3);
            String expected = "These are the following task(s) to complete:\n" +
                    "1. Task 1\n" +
                    "2. [✗] Timed Task(test): Timed Task 1(Time: " +
                    currentDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")\n" +
                    "3. [✗] Timed Task(test): Timed Task 2(Time: " +
                    currentDateTime.plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) +
                    ")\n";
            assertEquals(expected, outContent.toString());

            // Reset the TaskList
            taskList = new TaskList(tasks);
        } catch (Exception e) {
            fail();
        }
    }
}
