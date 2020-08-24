package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][\u2718] return book(by: 2019-10-15)", new Deadline("return book", "2019-10-15").toString());
    }
}
