package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void parseCommandTest() throws DukeException {
        Parser parser = new Parser();
        Choice actualOutput = parser.parseCommand("bye");
        assertEquals(Choice.BYE, actualOutput);
    }

    @Test
    public void parseCommandTest2() throws DukeException {
        Parser parser = new Parser();
        Choice actualOutput = parser.parseCommand("done 2");
        assertEquals(Choice.DONE, actualOutput);
    }
}
