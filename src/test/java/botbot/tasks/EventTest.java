package botbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void testStringConversion() {
        LocalDateTime dateWithTime = LocalDateTime.of(2021, 3, 17, 3, 45);
        LocalDateTime dateWithoutTime = LocalDateTime.of(2020, 5, 5, 3, 14, 15, 926535898);
        
        assertEquals("[E] [\u2718] test (at: 17 Mar 2021 3.45am)", new Event("test", dateWithTime).toString());
        assertEquals("[E] [\u2713] sample (at: 17 Mar 2021 3.45am)", new Event("sample", TaskStatus.DONE,
                dateWithTime.toString()).toString());
        assertEquals("[E] [\u2718] test (at: 5 May 2020)", new Event("test", TaskStatus.NOT_DONE,
                dateWithoutTime.toString()).toString());
    }
}
