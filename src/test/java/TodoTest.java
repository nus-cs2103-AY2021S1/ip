import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void test1() {
        assertEquals("homework", new Todo("homework").getMessage());
    }
}