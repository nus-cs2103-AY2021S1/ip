import duke.EventTask;
import duke.ParseErrorException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest{

    @Test
    public void getFromDateTimeTest() throws ParseErrorException {
        assertEquals("2020-01-01", new EventTask("Hello", "2020-01-01"));
    }
}