import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertTest {
    @Test
    public void test1() {
        assertEquals(Convert.by("return book /by 2020-01-01"),"return book (by: Jan 1 2020)");
    }
}