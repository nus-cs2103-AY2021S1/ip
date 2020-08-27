import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void getAtTest(){
        String expected = "2020-05-12 19:00 to 2020-05-12 22:00";
        String result = Parser.getAt("event town hall /at 2020-05-12 19:00 to 2020-05-12 22:00");
        assertEquals(expected, result);
    }

    @Test
    public void getLocalDateTime(){
        LocalDateTime expected = LocalDateTime.of(2020,7,19,15,00);
        LocalDateTime result = Parser.getLocalDateTimeBy("2020-07-19 15:00");
        assertEquals(expected, result);
    }
}
