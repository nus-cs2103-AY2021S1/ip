package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private TaskList testTasks;
    private static final String separator = System.getProperty("line.separator");
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Test
    public void testGetTasks() {
        ArrayList<Task> testTasks = new ArrayList<>();
        testTasks.add(new ToDo("description", false));
        testTasks.add(new ToDo("done", true));
        this.testTasks = new TaskList(testTasks);
        assertEquals(this.testTasks.getTasks(), testTasks);
    }
    
    @Test
    public void testAddTask() {
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        this.testTasks = new TaskList();
        Task newTask = new ToDo("to be done", false);
        this.testTasks.addTask(newTask);
        assertEquals(" Your task has been recorded." + separator +
                "   " + newTask + separator + " You have 1 tasks currently." +
                separator, out.toString());
        System.setOut(originalOut);
    }
    
    @Test
    public void testDeleteTask() {
        PrintStream ps = new PrintStream(out);
        this.testTasks = new TaskList();
        Task newTask = new ToDo("to be done", false);
        this.testTasks.addTask(newTask);
        System.setOut(ps);
        this.testTasks.deleteTask(-1);
        assertEquals(" Sorry I cannot find your specified task :(" +
                separator, out.toString());
        out.reset();
        this.testTasks.deleteTask(1);
        assertEquals(" Okay, I will remove this task for you" + separator +
                "   " + newTask + separator +
                " You have 0 tasks currently." + separator,
                out.toString());
        System.setOut(originalOut);
    }
    
    @Test
    public void testMarkAsDone() {
        PrintStream ps = new PrintStream(out);
        this.testTasks = new TaskList();
        Task newTask = new ToDo("to be done", false);
        this.testTasks.addTask(newTask);
        System.setOut(ps);
        this.testTasks.markAsDone(-1);
        assertEquals(" Sorry I cannot find your specified task :(" +
                separator, out.toString());
        out.reset();
        this.testTasks.markAsDone(1);
        assertEquals(" Congratulations for finishing this task!" + separator +
                " Let me mark this as done for you." + separator +
                "   " + newTask + separator, out.toString());
        System.setOut(originalOut);
    }
    
    @Test
    public void testListTasks() {
        PrintStream ps = new PrintStream(out);
        this.testTasks = new TaskList();
        System.setOut(ps);
        this.testTasks.listTasks();
        assertEquals(" You've got no tasks now." + separator +
                " If you want to get busy add more task." + separator +
                " I'll remember them for you :)" + separator, out.toString());
        Task newTask = new ToDo("to be done", false);
        this.testTasks.addTask(newTask);
        out.reset();
        this.testTasks.listTasks();
        assertEquals(" Let me list out all your tasks..." + separator +
                " 1." + newTask + separator, out.toString());
        System.setOut(originalOut);
    }
}
