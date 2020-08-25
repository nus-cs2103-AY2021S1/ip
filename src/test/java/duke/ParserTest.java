package duke;

import duke.command.*;
import duke.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void noCommandTest1() {
        assertThrows(UnrecognizedTaskException.class, () -> {
            Parser.parse("bleh");
        });
    }

    @Test
    public void noCommandTest2() {
        assertThrows(UnrecognizedTaskException.class, () -> {
            Parser.parse("bleh kdfhkdh");
        });
    }

    @Test
    public void noIndexTest1() {
        assertThrows(NoIndexException.class, () -> {
            Parser.parse("delete");
        });
    }

    @Test
    public void noIndexTest2() {
        assertThrows(NoIndexException.class, () -> {
            Parser.parse("done ");
        });
    }

    @Test
    public void emptyTaskTest1() {
        assertThrows(EmptyTaskException.class, () -> {
            Parser.parse("event ");
        });
    }

    @Test
    public void emptyTaskTest2() {
        assertThrows(EmptyTaskException.class, () -> {
            Parser.parse("deadline");
        });
    }

    @Test
    public void invalidDateTimeTest1() {
        assertThrows(InvalidDateException.class, () -> {
            Parser.getDateTime("08102020");
        });
    }

    @Test
    public void invalidDateTimeTest2() {
        assertThrows(InvalidDateException.class, () -> {
            Parser.getDateTime("2020-08-20T10.00");
        });
    }

    @Test
    public void toDoCommandTest() {
        try {
            assertEquals(Parser.parse("todo sleep"), new ToDoCommand("sleep"));
        }  catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void eventCommandTest() {
        try {
            assertEquals(Parser.parse("event bfast /at 10:00"),
                    new EventCommand("bfast /at 10:00"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deadlineCommandTest() {
        try {
            assertEquals(Parser.parse("deadline project /by 2020-08-27"),
                    new DeadlineCommand("project /by 2020-08-27"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void doneCommandTest() {
        try {
            assertEquals(Parser.parse("done 1"), new DoneCommand(1));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteCommandTest() {
        try {
            assertEquals(Parser.parse("delete 1"), new DeleteCommand(1));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
