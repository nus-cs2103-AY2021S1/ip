package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeadlinesTest {

    @Test
    void DeadlinePrintTimeTest() {
        Deadlines deadline = new Deadlines("deadline1", "2020-08-22 17:00", false);
        assertEquals("22 Aug 2020 05:00 PM", deadline.printTime());
    }

    @Test
    void DeadlineWriteToFileTest() {
        Deadlines deadline = new Deadlines("deadline2", "2020-08-22 17:00", false);
        assertEquals("D | 0 | deadline2 | 2020-08-22T17:00", deadline.writeToFile());
    }
}