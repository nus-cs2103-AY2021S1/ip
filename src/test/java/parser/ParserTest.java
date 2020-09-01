package parser;

import java.commands.*;
import java.exceptions.DukeException;
import java.parser.Parser;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void testNewDeadline(){
        try {
            Command command = Parser.parse("deadline laundry /by 2020-08-23");
            LocalDate date = LocalDate.parse("2020-08-23");
            AddCommand command1 = new AddCommand("deadline", "laundry", date);
            System.out.println(command1.equals(command));
            assertEquals(command1,command);
        } catch (DukeException ex) {
        }
    }

    @Test
    public void testNewTodo(){
        try {
            Command command = Parser.parse("todo laundry");
            AddCommand command1 = new AddCommand("todo", "laundry", null);
            System.out.println(command1.equals(command));
            assertEquals(command1,command);
        } catch (DukeException ex) {
        }
    }
}