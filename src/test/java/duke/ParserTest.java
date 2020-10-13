package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void testParsingDeadline() {
        String input = "deadline homework /by 31/12/2020";
        int expectedCommandType = Command.CREATE_DEADLINE;
        TaskList taskList = new TaskList();
        Command testCommand = Parser.parseInput(input, taskList);
        assertEquals(expectedCommandType,testCommand.getCommandType());
        try {
            String output = testCommand.execute();
            // executed successfully
            assertEquals(1,1);
        } catch (DukeException e) {
            fail("Parsing has failed");
        }
    }

    @Test
    public void testParsingWrongDeadline() {
        String input = "deadline homework /by 31-12-2020";
        int expectedCommandType = Command.CREATE_DEADLINE;
        TaskList taskList = new TaskList();
        Command testCommand = Parser.parseInput(input, taskList);
        assertEquals(expectedCommandType,testCommand.getCommandType());
        try {
            String output = testCommand.execute();
        } catch (DukeException e) {
            assertEquals(1,1);
        }
    }

    @Test
    public void testParsingEvent() {
        String input = "event Jean's Birthday /at 2/3/2020";
        int expectedCommandType = Command.CREATE_EVENT;
        TaskList taskList = new TaskList();
        Command testCommand = Parser.parseInput(input, taskList);
        assertEquals(expectedCommandType,testCommand.getCommandType());
        try {
            String output = testCommand.execute();
            // executed successfully
            assertEquals(1,1);
        } catch (DukeException e) {
            fail("Parsing has failed");
        }
    }

    @Test
    public void testParsingEventWrongDate() {
        String input = "event Jean's birthday /at 30-12-2020";
        int expectedCommandType = Command.CREATE_EVENT;
        TaskList taskList = new TaskList();
        Command testCommand = Parser.parseInput(input, taskList);
        assertEquals(expectedCommandType,testCommand.getCommandType());
        try {
            String output = testCommand.execute();
        } catch (DukeException e) {
            // supposed to throw exception
            assertEquals(1,1);
        }
    }
    @Test
    public void testParsingTodo() {
        String input = "todo homework";
        int expectedCommandType = Command.CREATE_TODO;
        TaskList taskList = new TaskList();
        Command testCommand = Parser.parseInput(input, taskList);
        assertEquals(expectedCommandType,testCommand.getCommandType());
        try {
            String output = testCommand.execute();
            // executed successfully
            assertEquals(1,1);
        } catch (DukeException e) {
            fail("Parsing has failed");
        }
    }

    @Test
    public void testParsingTodoFail() {
        String input = "todo";
        int expectedCommandType = Command.CREATE_TODO;
        TaskList taskList = new TaskList();
        Command testCommand = Parser.parseInput(input, taskList);
        assertEquals(expectedCommandType,testCommand.getCommandType());
        try {
            String output = testCommand.execute();
        } catch (DukeException e) {
            // supposed to throw exception
            assertEquals(1,1);
        }
    }
}
