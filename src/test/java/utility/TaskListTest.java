package utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void getPositionTest() {
        int actualOutput = TaskList.getPosition("random /", '/');
        assertEquals(7, actualOutput);
    }
}
