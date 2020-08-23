package duke.component;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void isValidDone_emptyInput_throwException() {
        try {
            Parser.isValidDone("done ", 5);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task to mark as done cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void isValidDone_withoutSpace_returnNegative1() {
        try {
            assertEquals(-1, Parser.isValidDone("done1", 4));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void isValidDone_nonIntegerInput_throwException() {
        try {
            Parser.isValidDone("done anything", 4);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task index should be a number.", e.getMessage());
        }

        try {
            Parser.isValidDone("done 1.3", 4);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task index should be a number.", e.getMessage());
        }
    }

    @Test
    public void isValidDone_nonPositiveInput_throwException() {
        try {
            Parser.isValidDone("done 0", 5);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task index should be a positive integer.", e.getMessage());
        }

        try {
            Parser.isValidDone("done -3", 5);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task index should be a positive integer.", e.getMessage());
        }
    }

    @Test
    public void isValidDone_exceedLimit_throwException() {
        try {
            Parser.isValidDone("done 8", 5);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task index does not exist.", e.getMessage());
        }
    }

    @Test
    public void isValidDone_validInput_indexOutput() {
        try {
            assertEquals(3, Parser.isValidDone("done 3", 5));
            assertEquals(5, Parser.isValidDone("done 5", 5));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void isValidDelete_emptyInput_throwException() {
        try {
            Parser.isValidDelete("delete ", 5);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task to mark to delete cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void isValidDelete_withoutSpace_returnNegative1() {
        try {
            assertEquals(-1, Parser.isValidDelete("delete1", 4));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void isValidDelete_nonIntegerInput_throwException() {
        try {
            Parser.isValidDelete("delete anything", 4);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task index should be a number.", e.getMessage());
        }

        try {
            Parser.isValidDelete("delete 1.3", 4);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task index should be a number.", e.getMessage());
        }
    }

    @Test
    public void isValidDelete_nonPositiveInput_throwException() {
        try {
            Parser.isValidDelete("delete 0", 5);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task index should be a positive integer.", e.getMessage());
        }

        try {
            Parser.isValidDelete("delete -3", 5);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task index should be a positive integer.", e.getMessage());
        }
    }

    @Test
    public void isValidDelete_exceedLimit_throwException() {
        try {
            Parser.isValidDelete("delete 8", 5);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task index does not exist.", e.getMessage());
        }
    }

    @Test
    public void isValidDelete_validInput_indexOutput() {
        try {
            assertEquals(3, Parser.isValidDelete("delete 3", 5));
            assertEquals(5, Parser.isValidDelete("delete 5", 5));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void generate_nonTaskInput_throwException() {
        try {
            Parser.generate("anything");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void generate_emptyDescription_throwException() {
        try {
            Parser.generate("todo");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }

        try {
            Parser.generate("todo ");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }

        try {
            Parser.generate("deadline");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The description of a deadline cannot be empty.", e.getMessage());
        }

        try {
            Parser.generate("deadline ");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The description of a deadline cannot be empty.", e.getMessage());
        }

        try {
            Parser.generate("event");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The description of an event cannot be empty.", e.getMessage());
        }

        try {
            Parser.generate("event ");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The description of an event cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void generate_noSpace_throwExceptionWithTip() {
        try {
            Parser.generate("todothings");
            fail();
        } catch (Exception e) {
            assertEquals("Do you mean 'todo things'", e.getMessage());
        }

        try {
            Parser.generate("deadlinethings");
            fail();
        } catch (Exception e) {
            assertEquals("Do you mean 'deadline things'", e.getMessage());
        }

        try {
            Parser.generate("eventthings");
            fail();
        } catch (Exception e) {
            assertEquals("Do you mean 'event things'", e.getMessage());
        }
    }

    @Test
    public void generate_noTimeIndicator_throwException() {
        try {
            Parser.generate("deadline things");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! Time should be specified", e.getMessage());
        }

        try {
            Parser.generate("event things");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! Time should be specified", e.getMessage());
        }
    }

    @Test
    public void generate_noTimeSpecification_throwException() {
        try {
            Parser.generate("deadline things /by");
            fail();
        } catch (Exception e) {
            assertEquals("The time specification cannot be empty.", e.getMessage());
        }

        try {
            Parser.generate("event things /at");
            fail();
        } catch (Exception e) {
            assertEquals("The time specification cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void generate_invalidDateFormat_throwException() {
        try {
            Parser.generate("deadline assignment /by 2020/08/31");
            fail();
        } catch (Exception e) {
            assertEquals("Invalid input date, please input as yyyy-mm-dd.", e.getMessage());
        }

        try {
            Parser.generate("event assignment /at 2020-08-01");
            fail();
        } catch (Exception e) {
            assertEquals("Invalid input datetime, please input as yyyy-MM-dd HH:mm.", e.getMessage());
        }
    }

    @Test
    public void generate_validInput_validTask() {
        try {
            Task t = Parser.generate("todo task");
            Task t2 = new ToDo("task");
            assertEquals(t2, t);

            Task d = Parser.generate("deadline assignment /by 2020-08-31");
            Task d2 = new Deadline("assignment", "2020-08-31");
            assertEquals(d2, d);

            Task e = Parser.generate("event meeting /at 2020-09-01 11:00");
            Task e2 = new Event("meeting", "2020-09-01 11:00");
            assertEquals(e2, e);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_byeCommand_byeCommand() {
        assertEquals(new ByeCommand("bye"), Parser.parse("bye"));
    }

    @Test
    public void parse_listCommand_listCommand() {
        assertEquals(new ListCommand("list"), Parser.parse("list"));
    }

    @Test
    public void parse_deleteCommand_deleteCommand() {
        assertEquals(new DeleteCommand("delete 3"), Parser.parse("delete 3"));
        assertEquals(new DeleteCommand("delete 0"), Parser.parse("delete 0"));
        assertEquals(new DeleteCommand("delete -3"), Parser.parse("delete -3"));
        assertEquals(new DeleteCommand("delete "), Parser.parse("delete "));
        assertEquals(new DeleteCommand("delete //"), Parser.parse("delete //"));
    }

    @Test
    public void parse_doneCommand_doneCommand() {
        assertEquals(new DoneCommand("done 3"), Parser.parse("done 3"));
        assertEquals(new DoneCommand("done 0"), Parser.parse("done 0"));
        assertEquals(new DoneCommand("done -3"), Parser.parse("done -3"));
        assertEquals(new DoneCommand("done "), Parser.parse("done "));
        assertEquals(new DoneCommand("done //"), Parser.parse("done //"));
    }

    @Test
    public void parse_happenCommand_happenCommand() {
        assertEquals(new HappenCommand("happen on 2020-08-31"), Parser.parse("happen on 2020-08-31"));
        assertEquals(new HappenCommand("happen "), Parser.parse("happen "));
    }

    @Test
    public void parse_taskCommand_addCommand() {
        assertEquals(new AddCommand("todo homework"), Parser.parse("todo homework"));
        assertEquals(new AddCommand("event anything"), Parser.parse("event anything"));
    }

    @Test
    public void parse_unrecognizedCommand_addCommand() {
        assertEquals(new AddCommand("bash"), Parser.parse("bash"));
        assertEquals(new AddCommand("happen"), Parser.parse("happen"));
    }
}
