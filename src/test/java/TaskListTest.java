import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTest() {
        TaskList myList = new TaskList();
        Todo testTodo = new Todo("test");
        myList.add(testTodo);
        assertEquals(myList.getTotalTasks(), 1);
    }

    @Test
    public void deleteTest() {
        TaskList myList = new TaskList();
        Todo testTodo = new Todo("test");
        Todo anotherTestTodo = new Todo("another test");
        myList.add(testTodo);
        myList.add(anotherTestTodo);
        myList.delete(2);
        assertEquals(myList.getTotalTasks(), 1);
    }
}
