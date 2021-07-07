package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void toString_normalInput_correctOutput() {
        Event event = new Event("event1", false, LocalDate.of(2021, 12, 31));
        assertEquals("[E][\u2718] event1 (at: 2021 Dec 31)", event.toString());
    }

    @Test
    void getDataString_noInput_correctOutput() {
        Event event = new Event("event1", false, LocalDate.of(2021, 12, 31));
        String[] expected = new String[] {"event", "false", "event1", "2021-12-31"};
        String[] actual = event.getDataStrings();
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < 3; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
