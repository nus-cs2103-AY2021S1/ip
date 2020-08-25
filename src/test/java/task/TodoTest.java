package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo task1 = new Todo("test", true);
    Todo task2 = new Todo("test triple words", false);

    @Test
    public void itemTest1(){
        assertEquals("[T][O]test", task1.getItem());
    }
    @Test
    public void itemTest2(){
        assertEquals("[T][X]test triple words", task2.getItem());
    }

    @Test
    public void inputTest1(){
        assertEquals("[T][O]test", task1.getInput());
    }
    @Test
    public void inputTest2(){
        assertEquals("[T][X]test triple words", task2.getInput());
    }
}
