import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void getBorder() {
        assertEquals(
        "____________________________________________________________\n",
                new Ui().getBorder());
    }
}
