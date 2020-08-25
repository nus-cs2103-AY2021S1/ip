package duke.dependencies.parser;

import duke.dependencies.dukeexceptions.DukeException;
import duke.dependencies.dukeexceptions.EmptyTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {

    @Test
    void test_Explicit_parseAndCheck_EmptyTODO_Exception() {
        String invalid = "todo run";
        try {
            Checker.parseAndCheck(invalid);
            fail("Empty todo should not be valid");
        } catch (EmptyTaskException e) {
            assertEquals("Error: Todo task cannot be empty", e.getMessage());
        } catch (DukeException e) {
            assertEquals("Error: Todo task cannot be empty", e.getMessage());
        }
    }

    @Test
    void test_Explicit_parseAndCheck_TODO_Valid() {
        String m = "todo run";
        try {
            Checker.parseAndCheck(m);
            assertEquals(1,1);
        } catch (DukeException e) {
            fail("Valid todo: 'todo run' should pass");
        }
    }

    @Test
    void test_Explicit_parseAndCheck_DEADLINE_Valid() {
        String m = "deadline return books /by 2020-08-13";
        try {
            Checker.parseAndCheck(m);
            assertEquals(1,1);
        } catch (DukeException e) {
            fail("Valid deadline: 'deadline return books /by 2020-08-13' should pass");
        }
    }

    @Test
    void test_Explicit_parseAndCheck_EVENT_Valid() {
        String m = "event meeting /at 2020-08-13";
        try {
            Checker.parseAndCheck(m);
            assertEquals(1,1);
        } catch (DukeException e) {
            fail("Valid event: 'event meeting /at 2020-08-13' should pass");
        }
    }

    @Test
    void test_Explicit_parseAndCheck_DONE_Valid() {
        String m = "done 1";
        try {
            Checker.parseAndCheck(m);
            assertEquals(1,1);
        } catch (DukeException e) {
            fail("Valid done: 'done 1' should pass");
        }
    }

    @Test
    void test_Explicit_parseAndCheck_LIST_Valid() {
        String m = "list";
        try {
            Checker.parseAndCheck(m);
            assertEquals(1,1);
        } catch (DukeException e) {
            fail("Valid list: how can this command even fail");
        }
    }

    @Test
    void test_Explicit_parseAndCheck_DELETE_Valid() {
        String m = "delete 1";
        try {
            Checker.parseAndCheck(m);
            assertEquals(1,1);
        } catch (DukeException e) {
            fail("Valid delete: 'delete 1' should pass");
        }
    }

}