import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    Parser p = new Parser();

    @Test
    public void testGetTaskNumber() {
        assertEquals(1, p.getTaskNumber( "done 1"));
        assertEquals(17, p.getTaskNumber( "delete 17"));
        assertEquals(299, p.getTaskNumber( "done 299"));
    }

    @Test
    public void testCommandToTask_completeInput_success() throws IncompleteInputException {
        assertEquals(new Todo("eat"), p.commandToTask("todo eat"));
        /*assertEquals(new Deadline("homework", "2020-08-30"),
                p.commandToTask("deadline homework /by 2020-08-30"));
        assertEquals(new Event("birthday", "2020-12-04"),
                p.commandToTask("event birthday /at 2020-12-4"));*/
    }

    @Test
    public void testCommandToTask_incompleteInput_exceptionThrown() throws IncompleteInputException {
        try {
            assertEquals(new Todo("eat"), p.commandToTask("todo"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("please finish your sentence.. :(\neither your task name is blank " +
                    "or youre missing the time!!", e.getMessage());
        }
        /*assertEquals(new Deadline("homework", "2020-08-30"),
                p.commandToTask("deadline homework /by 2020-08-30"));
        assertEquals(new Event("birthday", "2020-12-04"),
                p.commandToTask("event birthday /at 2020-12-4"));*/
    }
}
