import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void descDateConstructor_normalInput_success(){
        LocalDate date = LocalDate.parse("2020-08-24");
        Deadline deadline = new Deadline("family day", date);
        assertEquals("[D][0] family day (by: 2020-08-24)", deadline.toString());
    }

    @Test
    public void descDateDoneConstructor_normalInput_success(){
        LocalDate date = LocalDate.parse("2020-08-24");
        Deadline deadline = new Deadline("family day", true, date);
        assertEquals("[D][1] family day (by: 2020-08-24)", deadline.toString());
    }

    @Test
    public void display_normalInput_success(){
        LocalDate date = LocalDate.parse("2020-08-24");
        Deadline deadline = new Deadline("family day", date);
        assertEquals("[D][0] family day (by: Aug 24 2020)", deadline.display());
    }

    @Test
    public void descDateConstructor_emptyDesc_success(){
        LocalDate date = LocalDate.parse("2020-08-24");
        Deadline deadline = new Deadline("", date);
        assertEquals("[D][0]  (by: 2020-08-24)", deadline.toString());
    }
}
