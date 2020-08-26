import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Deadline d = new Deadline("submission", "2020-05-02", false);
        assertEquals("[D][\u2718] submission (by: May 2 2020)", d.toString());
    }
}
