package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskTest {

    @Test
    public void taskToTextTest() throws DukeException {

        assertEquals("T | 0 | do something",
                new Task("todo do something").taskToText());

        assertEquals("D | 0 | finish homework | 2020-10-10",
                new Task("deadline finish homework /by 2020-10-10").taskToText());


    }


}
