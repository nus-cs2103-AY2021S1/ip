import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventConstructorTest1(){ //tests basic constructor
        LocalDate date = LocalDate.parse("2020-08-24");
        Event event = new Event("do homework", date);
        assertEquals("[E][0] do homework (at: 2020-08-24)", event.toString());
    }

    @Test
    public void eventConstructorTest2(){ //test constructor that marks task as done
        LocalDate date = LocalDate.parse("2020-08-24");
        Event event = new Event("do homework", true, date);
        assertEquals("[E][1] do homework (at: 2020-08-24)", event.toString());
    }

    @Test
    public void eventDisplayTest(){ //tests display() method
        LocalDate date = LocalDate.parse("2020-08-24");
        Event event = new Event("do homework", true, date);
        assertEquals("[E][1] do homework (at: Aug 24 2020)", event.display());
    }

    @Test
    public void eventEmptyDescriptionTest(){ //tests display() method
        LocalDate date = LocalDate.parse("2020-08-24");
        Event event = new Event("", true, date);
        assertEquals("[E][1]  (at: Aug 24 2020)", event.display());
    }
}
