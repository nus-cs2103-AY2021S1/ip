package Ultron.Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void getTypeTest(){
        assertEquals("DEADLINE", new Deadline("deadline", "12pm").getType());
    }

    @Test
    public void getDateTestString(){
        assertEquals("12pm", new Deadline("hello", "12pm").getDate());
    }

    @Test
    public void getDateTestDate(){
        assertEquals("02-02-2002 1800", new Deadline("hello", "02-02-2002 1800").getDate());
    }

    @Test
    public void getCommandTestString(){
        assertEquals("hello /by 12pm", new Deadline("hello", "12pm").getCommand());
    }

    @Test
    public void getCommandTestDate(){
        assertEquals("hello /by 02-02-2002 1800", new Deadline("hello", "02-02-2002 1800").getCommand());
    }

    @Test
    public void parseCommandTest(){
        Task deadline = Deadline.parseCommand("hello /by 02-02-2002 1800");
        assertEquals("hello", deadline.getMessage());
        assertEquals("DEADLINE", deadline.getType());
        assertEquals("✘", deadline.getStatusIcon());
        assertEquals("[D][✘] hello (by: 02-02-2002 1800)", deadline.toString());
    }

}
