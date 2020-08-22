import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void toStringTest() {
        Todo dummyTodo = new Todo("go on date with gf");
        assertEquals("[T]" + "[\u2718]" + " go on date with gf", dummyTodo.toString());
    }
}
