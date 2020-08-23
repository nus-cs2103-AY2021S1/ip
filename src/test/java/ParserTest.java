import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;

public class ParserTest {
    @Test
    public void getNewTaskTest() throws DukeException {
        Parser parser = new Parser("event attend wedding /at 2019-12-10");
        String[] expectedOutput = new String[]{"E", "attend wedding", "2019-12-10"};
        assertEquals(Arrays.toString(expectedOutput), Arrays.toString(parser.getNewTask()));
    }
}
