import duke.tasklist.Deadline;
import duke.tasklist.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DukeTest class is used for JUnit testing.
 * @author Maguire Ong
 */
public class DukeTest {
    @Test
    public void dummy_Test(){
        assertEquals(2, 2);
    }

    @Test
    public void Todo_Test()  {
        assertEquals("[T][✘] borrow book", new Todo("borrow book", new ArrayList<String>()).toString());
    }

    @Test
    public void DeadlineTest() {
        String fullCommand = "return book /by 2019-10-15";
        int start = fullCommand.indexOf("/by");
        String date = fullCommand.substring(start + 4);
        LocalDate d = LocalDate.parse(date);
        String formattedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        Deadline t = new Deadline(fullCommand.substring(9, start - 1), formattedDate, addTags(fullCommand));
        assertEquals("[D][✘] ok (by: Oct 15 2019)", t.toString());
    }

    ArrayList<String> addTags(String fullCommand) {
        ArrayList<String> tags = new ArrayList<>();
        String[] s = fullCommand.split("\\s");
        for (int i=0; i<s.length; i++) {
            if (s[i].substring(0,1) == "#") {
                tags.add(s[i]);
            }
        }
        return tags;
    }
}
