import duke.DukeException;
import duke.Parser;
import duke.command.*;
import duke.task.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {

    @Test
    public void deadlineTest1() {
        Deadline deadline = new Deadline("return book", "2020-12-12 14:23");
        assertEquals("[D][✘] return book (by: Dec 12 2020 02:23 pm)", deadline.toString());
    }
    @Test
    public void deadlineTest2() {
        Deadline deadline = new Deadline("return book", "tomorrow");
        assertEquals("[D][✘] return book (by: tomorrow)", deadline.toString());
    }

    @Test
    public void todoTest1() {
        Todo todo = new Todo("return book");
        assertEquals("[T][✘] return book", todo.toString());
    }

    @Test
    public void completeTaskTest1() {
        Todo todo = new Todo("return book");
        assertEquals("[T][✘] return book", todo.toString());
        todo.markAsDone();
        assertEquals("[T][✓] return book", todo.toString());
    }

    @Test
    public void taskListTest1() {
        TaskList list = new TaskList();
        Todo todo = new Todo("return book");
        list.add(todo);
        assertEquals("[T][✘] return book", list.get(0).toString());
        list.get(0).markAsDone();
        assertEquals("[T][✓] return book", list.get(0).toString());
    }

    @Test
    public void eventTest1() {
        Event event = new Event("party", "2020-12-12");
        assertEquals("[E][✘] party (at: 2020-12-12)", event.toString());
    }

    @Test
    public void checkListCommandTest1() throws DukeException {
        try {
            Command c = Parser.parse("list");
            assertTrue(c instanceof ShowListCommand);
        } catch (DukeException e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    public void checkInvalidCommandTest1() throws DukeException {
        try {
            Command c = Parser.parse("bananananannaa");

        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! wait..... I don't understand your order my sir.", e.getMessage());
        }
    }

    @Test
    public void checkExitCommandTest1() throws DukeException {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }



}