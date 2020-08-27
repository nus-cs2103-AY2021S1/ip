package duke.test;


import duke.commands.Command;
import duke.tasks.TaskType;
import duke.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class ParserTest {

    @Test
    public void parseLineToArray_validText_correctArray() {
        String text = "hello my name is duke";
        String[] actual = Parser.parseLineToArray(text);
        String[] expected = {"hello", "my", "name", "is", "duke"};
        assertArrayEquals(expected, actual);
    }


    @Test
    public void parseLineToArray_emptyText_correctArray() {
        String text = "";
        String[] actual = Parser.parseLineToArray(text);
        String[] expected = {""};
        assertArrayEquals(expected, actual);
    }


    @Test
    public void getCommand_validCommand_correctCommand() {
        String text = "done 1";
        Command actual = Parser.getCommand(text);
        Command expected = Command.DONE;
        assertEquals(expected, actual);
    }


    @Test
    public void getCommand_validTask_correctCommand() {
        String text = "todo stuff abc";
        Command actual = Parser.getCommand(text);
        Command expected = Command.TASK;
        assertEquals(expected, actual);
    }
    
    @Test
    public void getCommand_invalidCommand_correctCommand() {
        String text = "XXX";
        Command actual = Parser.getCommand(text);
        Command expected = Command.INVALID;
        assertEquals(expected, actual);
    }
    
    @Test
    public void getTask_validTask_correctCommand() {
        String text = "todo stuff abc";
        TaskType actual = Parser.getTaskKeyword(text);
        TaskType expected = TaskType.TODO;
        assertEquals(expected, actual);
    }
    
    @Test
    public void getTask_invalidCommand_correctCommand() {
        String text = "XXX";
        TaskType actual = Parser.getTaskKeyword(text);
        TaskType expected = TaskType.INVALID;
        assertEquals(expected, actual);
    }

}
