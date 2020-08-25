package duke.dependencies.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskDateTest {

    @Test
    void test_isValidFormat_On_Correct_Date_Format() {
        String correct1 = "2019-05-08";
        String correct2 = "20/08/2020";
        assertAll("The date format should be valid.",
                () -> assertTrue(TaskDate.isValidFormat(correct1)),
                () -> assertTrue(TaskDate.isValidFormat(correct2))
                );
    }

    @Test
    void test_TaskDate_compareTo() {
        TaskDate d1 = new TaskDate("2019-05-08");
        TaskDate d2 = new TaskDate("20/08/2020");
        TaskDate d3 = new TaskDate("20/08/2020");
        assertAll(() -> assertEquals(0, d2.compareTo(d3)),
                () -> assertEquals(-1, d1.compareTo(d2)),
                () -> assertEquals(1, d2.compareTo(d1)));
    }

    @Test
    void testToString() {
        TaskDate d1 = new TaskDate("2019-05-08");
        TaskDate d2 = new TaskDate("20/08/2020");
        assertAll(() -> assertEquals("May 08 2019", d1.toString()),
                () -> assertEquals("Aug 20 2020", d2.toString()));
    }
}