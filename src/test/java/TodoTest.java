import duke.Todo;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getDescriptionTest(){
        assertEquals(Todo.getDescription("todo abcd"), "abcd");
    }
}
