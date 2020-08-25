import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void dummyTest(){
        Task t = new Task("test1", LocalDate.parse("2001-09-11"));
//        System.out.println(t.toString());
        assertEquals("[✗] test1", t.toString());
    }
}
