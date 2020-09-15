package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;

public class ParserTest {

    @Test
    public void testNewTodo() {
        try {
            Command command = Parser.parse("todo laundry");
            AddCommand command1 = new AddCommand("todo", "laundry", null);
            System.out.println(command1.equals(command));
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void testNewDeadline() {
        try {
            Command command = Parser.parse("deadline laundry /by 2020-08-23");
            LocalDate date = LocalDate.parse("2020-08-23");
            LocalDate[] dateArr = {date};
            AddCommand command1 = new AddCommand("deadline", "laundry", dateArr);
            assertEquals(command1, command);
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void testNewEvent() {
        try {
            Command command = Parser.parse("event zoo /at 2020-08-23");
            LocalDate date = LocalDate.parse("2020-08-23");
            LocalDate[] dateArr = {date};
            AddCommand command1 = new AddCommand("event", "zoo", dateArr);
            assertEquals(command1, command);
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void testNewPeriodTask1() {
        try {
            Command command = Parser.parse("period-task zoo /from 2020-08-23 /to 2020-08-25");
            LocalDate startDate = LocalDate.parse("2020-08-23");
            LocalDate endDate = LocalDate.parse("2020-08-25");
            LocalDate[] dateArr = {startDate, endDate};
            AddCommand command1 = new AddCommand("period-task", "zoo", dateArr);
            assertEquals(command1, command);
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void testNewPeriodTask2() {
        try {
            Command command = Parser.parse("period-task zoo /from 2020-08-23");
        } catch (DukeException ex) {
            String message = "Please specify start and end time using \"/from yyyy-mm-dd "
                    + "/to yyyy-mm-dd\". (´∀`)";
            assertEquals(ex.getMessage(), message);
        }
    }
}
