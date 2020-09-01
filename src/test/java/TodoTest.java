import Duke.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TodoTest {
    @Test
    public void Test1(){
        Todo t = new Todo("Task1");
        assertEquals(t.toString(), "[T][✘] Task1");
    }
    @Test
    public void Test2(){
        Todo t = new Todo("Task2");
        t.done();
        assertEquals(t.toString(), "[T][✓] Task2");
    }
}