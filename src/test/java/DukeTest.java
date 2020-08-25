import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void parserTestReturnError(){
        assertEquals(Parser.parse("dfgjdhg"), Command.error);
        assertEquals(Parser.parse("todo"), Command.error);
        assertEquals(Parser.parse("event"), Command.error);
    }

    @Test
    public void taskTestDateToString() {
        LocalDateTime temp = LocalDateTime.parse("3/3/03 0300", DateTimeFormatter.ofPattern("d/M/yy HHmm"));
        assertEquals(Task.dateToString(temp), "03 Mar 2003 3.00AM");
    }

    @Test
    public void taskListTestToStringEmpty() {
        TaskList temp = new TaskList(new ArrayList<Task>());
        assertEquals(temp.toString(),"you haven't added any tasks to the list yet!");
    }

}