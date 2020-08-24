package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void TodoSplitToStringTest(){
        LocalDate date = LocalDate.parse("2019-12-01");
        Event e = new Event("Submit IP", date);
        String actual = e.splitToString();
        String expected = "\nE/0/Submit IP/2019-12-01";
        assertEquals(expected, actual);
    }
}
