package rogue.logic.parser;

import rogue.logic.parser.exceptions.IncorrectInputException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void createExe_invalidArguments_exceptionThrown() {
        assertThrows(IncorrectInputException.class, () -> {
            Parser.createExe("done book");
        });

        assertThrows(IncorrectInputException.class, () -> {
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
