package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exception.EmptyDescriptionException;
import exception.EmptyTimeException;
import exception.InvalidCommandException;
import exception.InvalidIndexException;

public class ParserTest {
    @Test
    public void testParse() throws InvalidCommandException {
        String input = "todo read book";
        Commands command = Parser.parse(input);
        assertEquals(Commands.TODO, command);
    }

    @Test
    public void testParse_invalidCommand_exceptionThrown() throws InvalidCommandException {
        String input = "todooo read book";
        try {
            Commands command = Parser.parse(input);
            fail();
        } catch (InvalidCommandException ex) {
            assertEquals("Invalid use of Duke: ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "Available commands are: \n"
                + "1. todo <todo_desc>\n"
                + "2. deadline <deadline_desc> /by <time>\n"
                + "3. event <event_desc> /at <time>\n"
                + "4. done <task_no>\n"
                + "5. delete <task_no>\n"
                + "6. find <task_desc_or_tag>\n"
                + "7. tag <task_no> <tags_separated_by_comma>\n"
                + "8. list\n"
                + "9. bye", ex.getMessage());
        }
    }

    @Test
    public void testCheckIndex_invalidIndex_exceptionThrown() throws InvalidIndexException {
        String[] inputs = {"done", "4"};
        int numberOfTask = 2;
        try {
            Parser.checkIndex(inputs, numberOfTask);
            fail();
        } catch (InvalidIndexException ex) {
            assertEquals("Invalid use of Duke: Please input a valid index of task\n"
                + "Input a number between 1 - 2", ex.getMessage());
        }
    }

    @Test
    public void testCheckDescription_invalidFormat_exceptionThrown() throws EmptyDescriptionException {
        String command = "deadline /by tomorrow";
        String[] inputs = command.split("\\s+", 2);
        String temp = " " + inputs[1];
        String[] desc = temp.split("/by", 2);
        inputs[1] = desc[0];
        try {
            Parser.checkDescription(inputs, Commands.DEADLINE);
            fail();
        } catch (EmptyDescriptionException ex) {
            assertEquals("Invalid use of Duke: Please specify the task description\n"
                + "Please input using the format: deadline <deadline_desc> /by <time>", ex.getMessage());
        }
    }

    @Test
    public void testCheckTime_invalidFormat_exceptionThrown() throws EmptyTimeException {
        String command = "event meeting /at ";
        String[] inputs = command.split("\\s+", 2);
        String temp = " " + inputs[1];
        String[] desc = temp.split("/at", 2);
        inputs[1] = desc[0];
        try {
            Parser.checkTime(desc, Commands.EVENT);
            fail();
        } catch (EmptyTimeException ex) {
            assertEquals("Invalid use of Duke: Please specify the time for the task\n"
                + "Please input using the format: event <event_desc> /at <time>", ex.getMessage());
        }
    }

}
