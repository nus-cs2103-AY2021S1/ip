package duke.command;

import duke.Parser;
import duke.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {

    @Test
    public void commandTest1() {
        try {
            Command command = new TodoCommand("test1");
            assertEquals(command, Parser.parse("todo test1"));
        } catch (DukeException t) {
            TodoException todoExc = new TodoException();
            assertEquals(todoExc.getMessage(), t.getMessage());
        }
    }

    @Test
    public void commandTest2() {
        try {
            DeadlineCommand command = new DeadlineCommand("test2 /by 2020-09-08 16:00");
            assertEquals(command, Parser.parse("deadline test2 /by 2020-09-08 16:00"));
        } catch (CalendarException c) {
            CalendarException calendarExc = new CalendarException("Please enter the date in YYYY/MM/DD format and time in HH:MM format.");
            assertEquals(calendarExc.getMessage(), c.getMessage());
        } catch (DeadlineException t) {
            DeadlineException deadlineExc = new DeadlineException("Please specify the task and deadline.");
            assertEquals(deadlineExc.getMessage(), t.getMessage());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }

    @Test
    public void commandTest3() {
        try {
            EventCommand command = new EventCommand("test3 /at 2020-09-01 16:00-19:00");
            assertEquals(command, Parser.parse("event test3 /at 2020-09-01 16:00-19:00"));
        } catch (CalendarException c) {
            CalendarException calendarExc = new CalendarException("Please enter the date in YYYY/MM/DD format and time in HH:MM format.");
            assertEquals(calendarExc.getMessage(), c.getMessage());
        } catch (EventException t) {
            EventException eventExc = new EventException("Please specify the event name and date.");
            assertEquals(eventExc.getMessage(), t.getMessage());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
}
