import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testGetBy() {
        assertEquals("2021", new Deadline("sleep", "2021").getBy());
    }
}
