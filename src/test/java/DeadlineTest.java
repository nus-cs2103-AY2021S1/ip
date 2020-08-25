import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testEncode() {
String expectedOutput = "D|N|2019-12-02T18:00|return book";
Deadline toEncode = Deadline.createDeadline("return book /by 2/12/2019 1800");
String encoded = toEncode.encode();
assertEquals(expectedOutput, encoded);
    }
}
