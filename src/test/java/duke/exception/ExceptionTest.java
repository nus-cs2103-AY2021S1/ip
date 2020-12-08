package duke.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.Parser;

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
