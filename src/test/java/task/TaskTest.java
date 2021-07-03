package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task task1 = new Task("test", true);
    private Task task2 = new Task("test triple words", false);

    @Test
    public void itemTest1() {
        assertEquals("[O]test", task1.getItem());
    }
    @Test
    public void itemTest2() {
        assertEquals("[X]test triple words", task2.getItem());
    }

    @Test
    public void inputTest1() {
        assertEquals("test", task1.getInput());
    }
    @Test
    public void inputTest2() {
        assertEquals("test triple words", task2.getInput());
    }
}
