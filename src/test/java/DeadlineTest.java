import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    public void Test1(){
        Deadline t = new Deadline("Task1", LocalDateTime.of(2020, 1, 1, 12, 0));
        assertEquals(t.toString(), "[D][✘] Task1 (by: JAN 1 2020 12:00)");
    }
    @Test
    public void Test2(){
        Deadline t = new Deadline("Task2", LocalDateTime.of(2000, 12, 31, 17, 0));
        t.done();
        assertEquals(t.toString(), "[D][✓] Task2 (by: DEC 31 2000 17:00)");
    }
}