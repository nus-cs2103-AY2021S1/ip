import duke.model.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;


public class TodoTest {
    @Test
    public void test1() {
        Todo t = new Todo("Task1");
        assertEquals(t.toString(), "[T][N] Task1");
    }
    @Test
    public void test2() {
        Todo t = new Todo("Task2");
        t.done();
        assertEquals(t.toString(), "[T][Y] Task2");
    }
}
