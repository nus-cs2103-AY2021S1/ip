package duke.component;

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
}
