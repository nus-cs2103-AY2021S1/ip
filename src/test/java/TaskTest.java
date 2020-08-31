package test.java;

import main.java.duke.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getDescriptionTest(){
        assertEquals(new Task("abc").getDescription(), "abc");
    }
}
