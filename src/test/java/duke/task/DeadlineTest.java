package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DeadlineTest {

    @Test
    public void testConstructor() {
        assertEquals("[D][\u2717] Individual Project(at: 23 AUG 2020, SUN @ 18:00)",
                new Deadline("Individual Project",
                        LocalDateTime.of(2020, 8, 23, 18, 0)).toString());
    }

    @Test
    public void testCompletedTask() {
        Deadline dl = new Deadline("Individual Project",
                LocalDateTime.of(2020, 8, 23, 18, 0));
        dl.completeTask();

        assertEquals("[D][\u2713] Individual Project(at: 23 AUG 2020, SUN @ 18:00)", dl.toString());
    }
}
