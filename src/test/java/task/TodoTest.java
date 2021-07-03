package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private Todo task1 = new Todo("test", true);
    private Todo task2 = new Todo("test triple words", false);

    @Test
    public void itemTest1() {
        assertEquals("[T][O]test", task1.getItem());
    }
    @Test
    public void itemTest2() {
        assertEquals("[T][X]test triple words", task2.getItem());
    }

    @Test
    public void inputTest1() {
        assertEquals("[T][O]test", task1.getInput());
    }
    @Test
    public void inputTest2() {
        assertEquals("[T][X]test triple words", task2.getInput());
    }
}
