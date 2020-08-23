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
    public void isValidDone_ExceedLimit_throwException() {
        try {
            Parser.isValidDone("done 3", 5);
        } catch (Exception e) {
            fail();
        }

        try {
            Parser.isValidDone("done 8", 5);
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task index does not exist.", e.getMessage());
        }
    }
}
