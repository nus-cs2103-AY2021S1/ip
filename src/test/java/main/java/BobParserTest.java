package main.java;

import bob.Parser;
import bob.command.AddCommand;
import bob.command.Command;
import bob.exception.BobDateTimeParseException;
import bob.exception.BobException;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BobParserTest {
    @Test
    public void parse_Todo_success(){
        Command c = null;
        try {
            c = Parser.parse("todo do homework");
        } catch (BobException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals( new AddCommand(new Todo("do homework")), c);
    }

    @Test
    public void parse_Deadline_success() throws BobDateTimeParseException {
        Command c = null;
        try {
            c = Parser.parse("deadline do homework /by 2020-05-20 1700");
        } catch (BobException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new AddCommand(new Deadline("do homework", "2020-05-20 1700")),c);
    }

    @Test
    public void parse_Event_success() throws BobDateTimeParseException {
        Command c = null;
        try {
            c = Parser.parse("event do homework /at 2020-05-20 1700 to 2020-05-20 1800");
        } catch (BobException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new AddCommand(new Event("do homework", "2020-05-20 1700 to 2020-05-20 1800")), c);
    }

}
