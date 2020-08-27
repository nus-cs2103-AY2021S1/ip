package duke.test;


import duke.DukeList;
import duke.exceptions.DukeInvalidCommandException;
import duke.exceptions.DukeInvalidDescriptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DukeListTest {

    private static final char DONE = '\u2713';
    private static final char NOT_DONE = '\u2717';


    @Test
    public void add_correctInput_addedCorrectly() {
        DukeList testList = new DukeList();
        String actual = testList.add("todo stuff");
        String expected = "Got it. I've added this task:\n" +
                String.format("\t[T][%c] %s\n", NOT_DONE, "stuff") +
                String.format("%s", "Now you have 1 tasks in the list.");

        assertEquals(expected, actual);
    }


    @Test
    public void add_emptyDescription_exceptionThrown() {
        DukeList testList = new DukeList();

        assertThrows(DukeInvalidDescriptionException.class, () -> testList.add("todo"));
    }


    @Test
    public void add_emptyDate_exceptionThrown() {
        DukeList testList = new DukeList();

        assertThrows(DukeInvalidDescriptionException.class, () -> testList.add("deadline stuff"));

    }


    @Test
    public void add_invalidCommand_exceptionThrown() {
        DukeList testList = new DukeList();

        assertThrows(DukeInvalidCommandException.class, () -> testList.add("XXX"));
    }


    @Test
    public void markAsDone_validIndex_correctlyDeleted() {
        DukeList testList = new DukeList();
        testList.add("todo stuff");
        testList.add("deadline test /by 2020-01-01");
        testList.add("event another test /at 2020-02-02");

        String actual = testList.markAsDone(2);
        String expected = String.format("Nice! I've marked this task as done:\n\t[D][%c] test (by: 01 Jan 2020)", DONE);

        assertEquals(expected, actual);
    }


    @Test
    public void markAsDone_invalidIndex_exceptionThrown() {
        DukeList testList = new DukeList();
        testList.add("todo stuff");
        testList.add("deadline test /by 2020-01-01");
        testList.add("event another test /at 2020-02-02");

        assertThrows(IndexOutOfBoundsException.class, () -> testList.markAsDone(4));
    }


    @Test
    public void delete_validIndex_correctlyDeleted() {
        DukeList testList = new DukeList();
        testList.add("todo stuff");
        testList.add("deadline test /by 2020-01-01");
        testList.add("event another test /at 2020-02-02");

        String actual = testList.delete(1);
        String expected = "Noted. I've removed this task:\n" +
                String.format("\t[T][%c] %s\n", NOT_DONE, "stuff")
                + String.format("%s", "Now you have 2 tasks in the list.");

        assertEquals(expected, actual);
    }


    @Test
    public void delete_invalidIndex_exceptionThrown() {
        DukeList testList = new DukeList();
        testList.add("todo stuff");
        testList.add("deadline test /by 2020-01-01");
        testList.add("event another test /at 2020-02-02");

        assertThrows(IndexOutOfBoundsException.class, () -> testList.delete(4));
    }

}
