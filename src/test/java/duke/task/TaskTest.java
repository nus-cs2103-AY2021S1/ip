package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskTest {

    @Test
    public void taskToTextTest() {

        assertEquals("T | 0 | do something"
                , new Task("todo do something").taskToText());

        assertEquals("D | 0 | finish homework | 2020-10-10"
                , new Task("deadline finish homework /by 2020-10-10").taskToText());


    }


}
