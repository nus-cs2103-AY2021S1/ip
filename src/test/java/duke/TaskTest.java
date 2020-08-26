package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.ArrayList;

public class TaskTest {
    private static final String SEPARATOR = System.getProperty("line.separator");
    private final ByteArrayOutputStream OUT = new ByteArrayOutputStream();
    private final PrintStream ORIGINAL_OUT = System.out;
    private TaskList testTasks;
    
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
        PrintStream ps = new PrintStream(OUT);
        System.setOut(ps);
        testTasks = new TaskList();
        Task newTask = new ToDo("to be done", false);
        testTasks.addTask(newTask);
        assertEquals(" Your task has been recorded." + SEPARATOR +
                "   " + newTask + SEPARATOR + " You have 1 tasks currently." +
                SEPARATOR, OUT.toString());
        System.setOut(ORIGINAL_OUT);
    }
    
    @Test
    public void testDeleteTask() {
        PrintStream ps = new PrintStream(OUT);
        testTasks = new TaskList();
        Task newTask = new ToDo("to be done", false);
        testTasks.addTask(newTask);
        System.setOut(ps);
        testTasks.deleteTask(-1);
        assertEquals(" Sorry I cannot find your specified task :(" +
                SEPARATOR, OUT.toString());
        OUT.reset();
        testTasks.deleteTask(1);
        assertEquals(" Okay, I will remove this task for you" + SEPARATOR +
                "   " + newTask + SEPARATOR +
                " You have 0 tasks currently." + SEPARATOR,
                OUT.toString());
        System.setOut(ORIGINAL_OUT);
    }
    
    @Test
    public void testMarkAsDone() {
        PrintStream ps = new PrintStream(OUT);
        testTasks = new TaskList();
        Task newTask = new ToDo("to be done", false);
        testTasks.addTask(newTask);
        System.setOut(ps);
        testTasks.markAsDone(-1);
        assertEquals(" Sorry I cannot find your specified task :(" +
                SEPARATOR, OUT.toString());
        OUT.reset();
        testTasks.markAsDone(1);
        assertEquals(" Congratulations for finishing this task!" + SEPARATOR +
                " Let me mark this as done for you." + SEPARATOR +
                "   " + newTask + SEPARATOR, OUT.toString());
        System.setOut(ORIGINAL_OUT);
    }
    
    @Test
    public void testListTasks() {
        PrintStream ps = new PrintStream(OUT);
        testTasks = new TaskList();
        System.setOut(ps);
        testTasks.listTasks();
        assertEquals(" You've got no tasks now." + SEPARATOR +
                " If you want to get busy add more task." + SEPARATOR +
                " I'll remember them for you :)" + SEPARATOR, OUT.toString());
        Task newTask = new ToDo("to be done", false);
        testTasks.addTask(newTask);
        OUT.reset();
        testTasks.listTasks();
        assertEquals(" Let me list out all your tasks..." + SEPARATOR +
                " 1." + newTask + SEPARATOR, OUT.toString());
        System.setOut(ORIGINAL_OUT);
    }
}
