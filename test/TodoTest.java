import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    @Test
    void testToString() {
    Todo newTodo = new Todo("Read Asura Omega manga");
    assertEquals("[T][✘] Read Asura Omega manga",newTodo.toString());
    }
}