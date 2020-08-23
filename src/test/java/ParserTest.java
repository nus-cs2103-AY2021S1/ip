import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void loadDataToDoStringTest(){
        Parser parser = new Parser();
        Task task = parser.parseFileData("4.[T][\u2713] Watch TV");

        assertEquals("[T][\u2713] Watch TV", task.toString());
    }
    @Test
    void loadDataDeadlineTaskTest(){
        Parser parser = new Parser();
        Task task = parser.parseFileData("4.[D][\u2713] Assignment 3 (by: 12 Apr 1826)");

        assertEquals("Assignment 3", task.getTask());
    }

    @Test
    void loadDataDeadlineStringTest(){
        Parser parser = new Parser();
        Task task = parser.parseFileData("4.[D][\u2717] Watch TV (by: 05 Dec 2090)");

        assertEquals("[D][\u2717] Watch TV (by: 05 Dec 2090)", task.toString());
    }

    @Test
    void loadDataEventTaskTest(){
        Parser parser = new Parser();
        Task task = parser.parseFileData("4.[E][\u2713] Hackathon (at: Saturday whole day)");

        assertEquals("Hackathon", task.getTask());
    }

    @Test
    void loadDataEventStringTest(){
        Parser parser = new Parser();
        Task task = parser.parseFileData("4.[E][\u2713] Big Event (at: Friday 4-6pm)");

        assertEquals("[E][\u2713] Big Event (at: Friday 4-6pm)", task.toString());
    }

}

