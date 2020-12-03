package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;




public class DeadlineTest {
    @Test
    public void testingStringConversion() {
        Deadline deadline = new Deadline("return book", "2020-05-24");
        assertEquals("[D][✘] return book (by: May 24 2020)", deadline.toString());
        deadline.markAsDone();
        assertEquals("[D][✓] return book (by: May 24 2020)", deadline.toString());
    }
    @Test
    public void testingStringFileConversion() {
        Deadline deadline = new Deadline("return book", "2020-05-24");
        assertEquals("D | 0 | return book | 2020-05-24", deadline.toStringFile());
    }
}
