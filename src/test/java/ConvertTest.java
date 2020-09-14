import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.Converter;

public class ConvertTest {
    @Test
    public void test1() {
        assertEquals(Converter.by("return book /by 2020-01-01"),"return book (by: Jan 1 2020)");
    }
}