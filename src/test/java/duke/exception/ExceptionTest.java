package duke.exception;

import duke.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTest {

    @Test
    public void exceptionTest1() {
        assertThrows(WrongInputException.class, () ->
                Parser.parse("hehe"));
    }

    @Test
    public void exceptionTest2() {
        assertThrows(DukeException.class, () ->
                Parser.parse("todo "));
    }

}
