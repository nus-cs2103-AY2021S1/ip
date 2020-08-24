import java1.tasklist.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void todoTest() {
        assertEquals("[T][✘] buy groceries", new Todos("buy groceries").toString());
    }

    @Test
    public void eventTest() {
        String by = "12/09/2020 20:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
        Events e = new Events("concert", dateTime);
        assertEquals("[E][✘] concert (at: 12 Sep 2020 20:00)", e.toString() );
    }
}
