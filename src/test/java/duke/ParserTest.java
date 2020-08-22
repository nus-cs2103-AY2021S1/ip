package duke;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddToDoCommand;
import command.DeleteCommand;
import command.ExitCommand;
import command.MarkDoneCommand;
import command.TaskListCommand;
import command.UnknownCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseAddDeadline(){
        String[] splitCommand = {"deadline", "homework /by 18/02/2020 1900"};
        assertEquals(Parser.parse("deadline homework /by 18/02/2020 1900"), new AddDeadlineCommand(splitCommand));
    }

    @Test
    public void parseAddEvent(){
        String[] splitCommand = {"event", "workshop /at 18/02/2020 1900"};
        assertEquals(Parser.parse("event workshop /at 18/02/2020 1900"), new AddEventCommand(splitCommand));
    }

    @Test
    public void parseAddToDo(){
        String[] splitCommand = {"todo", "watch TV"};
        assertEquals(Parser.parse("todo watch TV"), new AddToDoCommand(splitCommand));
    }

    @Test
    public void parseDeleteCommand(){
        String[] splitCommand = {"delete", "1"};
        assertEquals(Parser.parse("delete 1"), new DeleteCommand(splitCommand));
    }

    @Test
    public void parseExit(){
        String[] splitCommand = {"bye"};
        assertEquals(Parser.parse("bye"), new ExitCommand(splitCommand));
    }

    @Test
    public void parseMarkDone(){
        String[] splitCommand = {"done", "3"};
        assertEquals(Parser.parse("done 3"), new MarkDoneCommand(splitCommand));
    }

    @Test
    public void parseTaskList(){
        String[] splitCommand = {"list"};
        assertEquals(Parser.parse("list"), new TaskListCommand(splitCommand));
    }

    @Test
    public void parseUnknown(){
        String[] splitCommand = {"hehehe"};
        assertEquals(Parser.parse("hehehe"), new UnknownCommand(splitCommand));
    }

}