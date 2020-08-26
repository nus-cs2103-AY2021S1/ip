import duke.Deadline;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getDescriptionTest(){
        assertEquals(Deadline.getDescription("deadline abcd /by 1111-11-11 11:11"), "abcd");
    }

    @Test
    public void getTimeTest(){
        assertEquals(Deadline.getTime("deadline abcd /by 1111-11-11 11:11"), "1111-11-11 11:11");
    }

    @Test
    public void changeDateFormatTest(){
        String[] s = {"/by", "1111/11/11"};
        assertEquals(Deadline.changeDateFormat(s), "1111-11-11");
    }
}
