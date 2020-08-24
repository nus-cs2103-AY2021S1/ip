import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineConstructorTest1(){ //tests basic constructor
        LocalDate date = LocalDate.parse("2020-08-24");
        Deadline deadline = new Deadline("family day", date);
        assertEquals("[D][0] family day (by: 2020-08-24)", deadline.toString());
    }

    @Test
    public void deadlineConstructorTest2(){ //test constructor that marks task as done
        LocalDate date = LocalDate.parse("2020-08-24");
        Deadline deadline = new Deadline("family day", true, date);
        assertEquals("[D][1] family day (by: 2020-08-24)", deadline.toString());
    }

    @Test
    public void deadlineDisplayTest(){ //tests display() method
        LocalDate date = LocalDate.parse("2020-08-24");
        Deadline deadline = new Deadline("family day", date);
        assertEquals("[D][0] family day (by: Aug 24 2020)", deadline.display());
    }

    @Test
    public void deadlineEmptyDescriptionTest(){ //tests display() method
        LocalDate date = LocalDate.parse("2020-08-24");
        Deadline deadline = new Deadline("", date);
        assertEquals("[D][0]  (by: 2020-08-24)", deadline.toString());
    }
}
