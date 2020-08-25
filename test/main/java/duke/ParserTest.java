package main.java.duke;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    @Test
    public void interpretInput_valid_success() {
        Parser parser = new Parser();
        String test1 = Arrays.toString(parser.interpretInput("todo homework"));
        assertEquals(test1,"[todo, homework]");

        String test2 = Arrays.toString(parser.interpretInput("event test2 /at 2020-09-08 18:00 20:00"));
        assertEquals(test2,"[event, test2 , 2020-09-08 18:00 20:00]");

        String test3 = Arrays.toString(parser.interpretInput("deadline assignment 3 /by 2020-08-25 23:59"));
        assertEquals(test3,"[deadline, assignment 3 , 2020-08-25 23:59]");
    }
}