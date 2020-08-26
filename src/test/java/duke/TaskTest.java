package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import java.util.ArrayList;

public class TaskTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    @Test 
    public void testInitialisation() {
        ArrayList<Task> loadedList = new ArrayList<>();
        loadedList.add(new ToDo("test"));
        TaskList list = new TaskList(loadedList);
        assertEquals(1, list.getStoredTasks().size());
    }
    
    @Test 
    public void testAddTask() {
        ArrayList<Task> loadedList = new ArrayList<>();
        TaskList list = new TaskList(loadedList);
        String input = "todo test";
        ToDo dummyToDo = new ToDo("test");
        list.addTask(input);
        assertEquals(dummyToDo.toString(), list.getStoredTasks().get(0).toString());
    }
    
    @Test 
    public void testConquerTask() {
        ArrayList<Task> loadedList = new ArrayList<>();
        ToDo dummyToDo = new ToDo("test");
        loadedList.add(dummyToDo);
        TaskList list = new TaskList(loadedList);
        
        String input = "conquer 1";
        list.conquerTask(input.split(" ", 2));
        assertEquals("[T][✓] test", list.getStoredTasks().get(0).toString());
    }
    
    @Test 
    public void testDeleteTask() {
        ArrayList<Task> loadedList = new ArrayList<>();
        ToDo dummyToDo = new ToDo("test");
        loadedList.add(dummyToDo);
        TaskList list = new TaskList(loadedList);
        assertEquals(1, list.getStoredTasks().size());
        String input = "delete 1";
        list.deleteTask(input.split(" ", 2));
        assertEquals(0, list.getStoredTasks().size());
    }
    
}
