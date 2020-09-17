package botbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        LocalDateTime dateWithTime = LocalDateTime.of(2021, 3, 17, 3, 45);
        LocalDateTime dateWithoutTime = LocalDateTime.of(2020, 5, 5, 3, 14, 15, 926535898);
        
        assertEquals("[D] [\u2717] test (by: 17 Mar 2021 3.45am)", new Deadline("test", dateWithTime).toString());
        assertEquals("[D] [\u2713] sample (by: 17 Mar 2021 3.45am)", new Deadline("sample", TaskStatus.DONE,
                dateWithTime.toString()).toString());
        assertEquals("[D] [\u2717] test (by: 5 May 2020)", new Deadline("test", TaskStatus.NOT_DONE,
                dateWithoutTime.toString()).toString());
    }
}
