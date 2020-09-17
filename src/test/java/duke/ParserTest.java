package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParsingDeadline() {
        String input = "deadline homework /by 31/12/2020 2359";
        String expectedTime = "2359";
        LocalDate expectedDate = LocalDate.parse("2020-12-31");
        String expectedDescription = "homework";
//        Command result = Parser.parseInput(input);
//        AdditionalInfo resultInfo = result.getAdditionalInfo();
//        assertEquals(expectedTime, resultInfo.getTime());
//        assertEquals(expectedDescription, resultInfo.getTaskDescription());
//        assertEquals(expectedDate, resultInfo.getDate());
//        assertEquals(Command.CREATE_DEADLINE, result.getCommandType());
    }

    @Test
    public void testParsingEvent() {
        String input = "event Jean's Birthday /at Sentosa tomorrow";
        String expectedTime = "Sentosa tomorrow";
        String expectedDescription = "Jean's Birthday";
//        Command result = Parser.parseInput(input);
//        AdditionalInfo resultInfo = result.getAdditionalInfo();
//        assertEquals(expectedDescription, resultInfo.getTaskDescription());
//        assertEquals(expectedTime, resultInfo.getTime());
//        assertEquals(Command.CREATE_EVENT, result.getCommandType());
    }

    @Test
    public void testParsingTodo() {
        String input = "todo finish my 2103 assignments!";
        String expectedDescription = "finish my 2103 assignments!";
//        Command result = Parser.parseInput(input);
//        AdditionalInfo resultInfo = result.getAdditionalInfo();
//        assertEquals(expectedDescription, resultInfo.getTaskDescription());
//        assertEquals(Command.CREATE_TODO, result.getCommandType());

    }
}
