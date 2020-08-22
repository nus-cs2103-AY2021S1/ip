package main.java;

import Command.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parserPrintListCommand(){
        String[] command = {"list"};
        assertEquals(Parser.parse("list"), new PrintListCommand(command));
    }

    @Test
    public void parserDeadlineCommand(){
        String[] command = {"deadline", "homework /by 29/08/2020 1800"};
        assertEquals(Parser.parse("deadline homework /by 29/08/2020 1800"), new AddDeadlineCommand(command));
    }

    @Test
    public void parserEventCommand(){
        String[] command = {"event", "homework /by 29/08/2020 1800"};
        assertEquals(Parser.parse("event homework /by 29/08/2020 1800"), new AddEventCommand(command));
    }

    @Test
    public void parserTodoCommand(){
        String[] command = {"todo", "homework /by 29/08/2020 1800"};
        assertEquals(Parser.parse("todo homework /by 29/08/2020 1800"), new AddTodoCommand(command));
    }

    @Test
    public void parserDeleteCommand(){
        String[] command = {"delete", "1"};
        assertEquals(Parser.parse("delete 1"), new DeleteCommand(command));
    }

    @Test
    public void parserDoneCommand(){
        String[] command = {"done", "1"};
        assertEquals(Parser.parse("done 1"), new DoneCommand(command));
    }

    @Test
    public void parserExitCommand(){
        String[] command = {"bye"};
        assertEquals(Parser.parse("bye"), new ExitCommand(command));
    }

    @Test
    public void parserUnknownCommand(){
        String[] command = {"unknown"};
        assertEquals(Parser.parse("unknown"), new UnknownCommand(command));
    }
}



