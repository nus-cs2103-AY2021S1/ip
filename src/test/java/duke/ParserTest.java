package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_bye_bye() throws Exception {
        assertEquals("BYE", Parser.parse("bye").toString());
    }

    @Test
    public void parse_list_list() throws Exception {
        assertEquals("LIST", Parser.parse("list").toString());
    }

    @Test
    public void parse_done1_done1() throws Exception {
        assertEquals("DONE 1", Parser.parse("done 1").toString());
    }

    @Test
    public void parse_delete2_delete2() throws Exception {
        assertEquals("DELETE 2", Parser.parse("delete 2").toString());
    }

    @Test
    public void parse_todo_todo() throws Exception {
        assertEquals("TODO [read book]", Parser.parse("todo read book").toString());
    }

    @Test
    public void parse_deadline_deadline() throws Exception {
        assertEquals("DEADLINE [return book, 6th June 2020]",
                Parser.parse("deadline return book /by 6th June 2020").toString());
    }

    @Test
    public void parse_event_event() throws Exception {
        assertEquals("EVENT [meeting, time]",
                Parser.parse("event meeting /at time").toString());
    }

    @Test
    public void parse_caseInsensitive_success() throws Exception {
        assertEquals("DEADLINE [case, insensitive]",
                Parser.parse("dEaDlInE case /by insensitive").toString());
    }

    @Test
    public void parse_doneBadInteger_exceptionThrown() {
        try {
            assertEquals("DONE 3", Parser.parse("done three").toString());
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, that is not a valid task.", e.getMessage());
        }
    }

    @Test
    public void parse_deleteNoNextScannerToken_exceptionThrown() {
        try {
            assertEquals("DELETE", Parser.parse("delete").toString());
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, that is not a valid task.", e.getMessage());
        }
    }

    @Test
    public void parse_todoNoNextScannerToken_exceptionThrown() {
        try {
            assertEquals("TODO", Parser.parse("todo").toString());
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, the description of a task cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineNoNextScannerToken_exceptionThrown() {
        try {
            assertEquals("DEADLINE", Parser.parse("deadline").toString());
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, the description of a task cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineNoBySubstring_exceptionThrown() {
        try {
            assertEquals("DEADLINE [do this, tonight]",
                    Parser.parse("deadline do this tonight").toString());
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, the description of a Deadline must be in this format:\n" +
                    "\tdeadline [task name] /by [deadline]", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWrongBySubstring_exceptionThrown() {
        try {
            assertEquals("DEADLINE [do this, tonight]",
                    Parser.parse("deadline do this by tonight").toString());
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, the description of a Deadline must be in this format:\n" +
                    "\tdeadline [task name] /by [deadline]", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineFakeBySubstring_success() throws Exception {
        assertEquals("DEADLINE [swing by Mom's, tomorrow]",
                Parser.parse("deadline swing by Mom's /by tomorrow").toString());
    }

    @Test
    public void parse_eventNoNextScannerToken_exceptionThrown() {
        try {
            assertEquals("EVENT", Parser.parse("event").toString());
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, the description of a task cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_eventNoAtSubstring_exceptionThrown() {
        try {
            assertEquals("EVENT [do this, 6pm]",
                    Parser.parse("event do this 6pm").toString());
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, the description of an Event must be in this format:\n" +
                    "\tevent [task name] /at [time]", e.getMessage());
        }
    }

    @Test
    public void parse_eventWrongAtSubstring_exceptionThrown() {
        try {
            assertEquals("EVENT [do this, 6pm]",
                    Parser.parse("event do this at 6pm").toString());
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, the description of an Event must be in this format:\n" +
                    "\tevent [task name] /at [time]", e.getMessage());
        }
    }

    @Test
    public void parse_eventFakeAtSubstring_success() throws Exception {
        assertEquals("EVENT [stay at home, all times]",
                Parser.parse("event stay at home /at all times").toString());
    }
}
