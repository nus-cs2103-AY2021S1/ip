package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TaskInheritanceTest {

    @Test
    public void testEventInheritance() {
        Task task = new Event("Individual Project",
                LocalDateTime.of(2020, 8, 23, 18, 0));
        assertEquals("[E][\u2717] Individual Project(at: 23 AUG 2020, SUN @ 18:00)",
                task.toString());
    }

    @Test
    public void testDeadlineInheritance() {
        Task task = new Deadline("Individual Project",
                LocalDateTime.of(2020, 8, 23, 18, 0));
        assertEquals("[D][\u2717] Individual Project(at: 23 AUG 2020, SUN @ 18:00)",
                task.toString());
    }
}
