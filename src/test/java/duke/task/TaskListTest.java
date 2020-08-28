package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTest_success() {
        Task testTask = new ToDo("this");
        TaskList testUnit = new TaskList();
        testUnit.add(testTask);
        assertEquals(testUnit.getList().get(0).toString(), "[T][✘] this");
    }
    
    @Test
    public void deleteTest_success() {
        Task testTask = new ToDo("this");
        TaskList testUnit = new TaskList();
        testUnit.add(testTask);
        testUnit.delete(1);
        assertEquals(testUnit.getList().size(), 0);
    }
    
    @Test 
    public void sizeTest_success() {
        TaskList testUnit = new TaskList();
        assertEquals(testUnit.size(), 0);
    }
    
    @Test
    public void getTest_success() {
        Task testTask = new ToDo("this");
        TaskList testUnit = new TaskList();
        testUnit.add(testTask);
        assertEquals(testUnit.get(1).toString(), "[T][✘] this");
    }
}
