package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddToDoCommand;
import command.DeleteCommand;
import command.ExitCommand;
import command.MarkDoneCommand;
import command.TaskListCommand;
import command.UnknownCommand;

public class ParserTest {
    @Test
    public void parseAddDeadline_correctInput_parsedCommandReturned() {
        String[] splitCommand = {"deadline", "homework /by 18/02/2020 1900"};
        assertEquals(Parser.parse("deadline homework /by 18/02/2020 1900"), new AddDeadlineCommand(splitCommand));
    }

    @Test
    public void parseAddEvent_correctInput_parsedCommandReturned() {
        String[] splitCommand = {"event", "workshop /at 18/02/2020 1900"};
        assertEquals(Parser.parse("event workshop /at 18/02/2020 1900"), new AddEventCommand(splitCommand));
    }

    @Test
    public void parseAddToDo_correctInput_parsedCommandReturned() {
        String[] splitCommand = {"todo", "watch TV"};
        assertEquals(Parser.parse("todo watch TV"), new AddToDoCommand(splitCommand));
    }

    @Test
    public void parseDeleteCommand_correctInput_parsedCommandReturned() {
        String[] splitCommand = {"delete", "1"};
        assertEquals(Parser.parse("delete 1"), new DeleteCommand(splitCommand));
    }

    @Test
    public void parseExit_correctInput_parsedCommandReturned() {
        String[] splitCommand = {"bye"};
        assertEquals(Parser.parse("bye"), new ExitCommand(splitCommand));
    }

    @Test
    public void parseMarkDone_correctInput_parsedCommandReturned() {
        String[] splitCommand = {"done", "3"};
        assertEquals(Parser.parse("done 3"), new MarkDoneCommand(splitCommand));
    }

    @Test
    public void parseTaskList_correctInput_parsedCommandReturned() {
        String[] splitCommand = {"list"};
        assertEquals(Parser.parse("list"), new TaskListCommand(splitCommand));
    }

    @Test
    public void parseUnknown_correctInput_parsedCommandReturned() {
        String[] splitCommand = {"hehehe"};
        assertEquals(Parser.parse("hehehe"), new UnknownCommand(splitCommand));
    }

}
