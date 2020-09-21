package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void eventConstructor_eventWithNameAndDate(){
        assertEquals("[E][\u2718] do project (at: Oct 24 2020)",
                new Event("do project", "2020-10-24").toString());
    }
}
