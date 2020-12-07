package main.java.duke;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import duke.Parser;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    public void interpretInput_valid_success() {
        Parser parser = new Parser();
        String test1 = Arrays.toString(parser.splitIntoComponents("todo homework"));
        assertEquals(test1,"[todo, homework]");

        String test2 = Arrays.toString(parser.splitIntoComponents("event test2 /at 2020-09-08 18:00 20:00"));
        assertEquals(test2,"[event, test2 , 2020-09-08 18:00 20:00]");

        String test3 = Arrays.toString(parser.splitIntoComponents("deadline assignment 3 /by 2020-08-25 23:59"));
        assertEquals(test3,"[deadline, assignment 3 , 2020-08-25 23:59]");
    }
}