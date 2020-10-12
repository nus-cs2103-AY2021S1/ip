package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.Event;


public class EventTest {
    private String[] split = "event return book /at 2019-10-15".substring(6).split(" /at ");
    private String eventDesc = split[0];
    private LocalDate eventTime = LocalDate.parse(split[1]);
    
    @Test
    public void eventConstructor_eventWithNameAndDate() {
        assertEquals("[E][\u2718] return book (at: Oct 15 2019)",
                new Event(eventDesc, eventTime, false).toString());
    }
}

