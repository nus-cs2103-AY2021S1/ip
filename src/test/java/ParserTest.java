import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_validInput_success() throws Exception {
        Command test = Parser.parse("check date and time");
        assertEquals(true, test instanceof CheckCommand);
    }

    @Test
    public void parse_invalidInput_exceptionThrown() {
        try {
            Command test = Parser.parse("onlyOneWord");
            fail();
        } catch (Exception e) {
            assertEquals("The description of the task cannot be empty!", e.getMessage());
        }
    }
}
