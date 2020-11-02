package butler.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTaskTest {

    @Test
    public void getTimeTest() {
        EventTask eventTask = new EventTask("summary", LocalDate.parse("2020-02-12"), LocalDate.parse("2021-12-12"));
        assertEquals("12 FEBRUARY 2020 to 12 DECEMBER 2021", eventTask.getEventPeriod());
    }
}
