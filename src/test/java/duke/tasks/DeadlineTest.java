package test.java.duke.tasks;

import main.java.duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void constructorTest() {
        Deadline deadlineTask = new Deadline("read book ",
                LocalDateTime.parse("2020-10-10 10:10", formatter));
        assertEquals("[D][✘] read book (by:10/10/2020 10:10 AM)", deadlineTask.toString());
    }

    @Test
    void getState() {
        Deadline deadlineTask = new Deadline("read book ",
                LocalDateTime.parse("2020-10-10 10:10", formatter));
        assertEquals("D|0|read book |2020-10-10 10:10", deadlineTask.getState());
    }

}