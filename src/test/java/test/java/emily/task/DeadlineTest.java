package test.java.emily.task;

import main.java.emily.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString() {
        assertEquals("[D][✘] return book(by: Sep 9 2020)", new Deadline("return book", "2020-09-09").toString());
    }
}