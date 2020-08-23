import org.junit.jupiter.api.Test;
import tasklist.Deadline;
import tasklist.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void TodoTest()  {
        assertEquals("[T][✘] borrow book", new Todo("borrow book").toString());
    }

    @Test
    public void DeadlineTest() {
        String fullCommand = "return book /by 2019-10-15";
        int start = fullCommand.indexOf("/by");
        String date = fullCommand.substring(start + 4);
        LocalDate d = LocalDate.parse(date);
        String formattedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        Deadline t = new Deadline(fullCommand.substring(9, start - 1), formattedDate);
        assertEquals("[D][✘] ok (by: Oct 15 2019)", t.toString());
    }
}
