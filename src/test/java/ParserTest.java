import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import duke.Command;
import duke.Parser;
import duke.TaskType;

/**
 * A test class wrapping around the test methods for the class Parser.
 */
public class ParserTest {
    @Test
    public void simpleTodo_todoReadBook_Success() {
        assertEquals(new Command(TaskType.TODO, "read book"), Parser.parse("todo read book"));
    }

    @Test
    public void deadlineWithDate_Success() {
        assertEquals(new Command(TaskType.DEADLINE, "return book", LocalDate.of(2020,12,25))
            , Parser.parse("deadline return book /by 2020-12-25"));
    }
}
