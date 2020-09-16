import Task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void test1(){
        Todo t = new Todo("test todo");
        System.out.println(t.toString());
        assertEquals("[T][✗] test todo\n", t.toString());
    }
}
