package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void getPositionTest() {
        int actualOutput = TaskList.getPosition("random /", '/');
        assertEquals(7, actualOutput);
    }
}
