package rogue.logic.parser;

import org.junit.jupiter.api.Test;

import rogue.logic.parser.exceptions.IncorrectInputException;

import rogue.logic.directives.exceptions.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void createExe_invalidArguments_exceptionThrown() {
        assertThrows(ExecutionException.class, () -> {
            Parser.createExe("done book");
        });

        assertThrows(ExecutionException.class, () -> {
            Parser.createExe("delete note");
        });
    }

    @Test
    public void createExe_invalidInput_exceptionThrown() {
        assertThrows(IncorrectInputException.class, () -> {
            Parser.createExe("create todo borrow book");
        });
    }
}
