import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void processMsg_invalidCommand_exceptionThrown() {
        try {
            Parser p = new Parser(new TaskList());
            p.processMsg("test");
        } catch (Exception e) {
            assertEquals("Specified action is not recognised.", e.getMessage());
        }
    }

    @Test
    public void processDate_validDate_success() throws DukeException {
        Parser p = new Parser(new TaskList());
        LocalDateTime test = p.processDate("5/2/2020 1821");
        assertEquals(LocalDateTime.of(2020, 2, 5, 18, 21), test);
    }
}
