package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class EventTest {

    @Test
    public void testConstructor() {
        assertEquals("[E][\u2717] Individual Project(at: 23 AUG 2020, SUN @ 18:00)",
                new Event("Individual Project",
                        LocalDateTime.of(2020, 8, 23, 18, 0)).toString());
    }

    @Test
    public void testCompletedTask() {
        Event dl = new Event("Individual Project",
                LocalDateTime.of(2020, 8, 23, 18, 0));
        dl.completeTask();

        assertEquals("[E][\u2713] Individual Project(at: 23 AUG 2020, SUN @ 18:00)", dl.toString());
    }
}
