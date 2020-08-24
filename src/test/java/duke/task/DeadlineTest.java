package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void TodoSplitToStringTest(){
        LocalDate date = LocalDate.parse("2019-12-01");
        Deadline dl = new Deadline("Submit IP", date);
        String actual = dl.splitToString();
        String expected = "\nD/0/Submit IP/2019-12-01";
        assertEquals(expected, actual);
    }
}
