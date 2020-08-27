package rogue.logic.parser;

import org.junit.jupiter.api.Test;

import rogue.logic.parser.exceptions.UnknownCommandException;

import rogue.commons.exceptions.IncorrectArgumentException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void createExe_invalidArguments_exceptionThrown() {
        assertThrows(IncorrectArgumentException.class, () -> {
            Parser.createExe("done book");
        });

        assertThrows(IncorrectArgumentException.class, () -> {
            Parser.createExe("delete note");
        });
    }

    @Test
    public void createExe_invalidInput_exceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> {
            Parser.createExe("create todo borrow book");
        });
    }
}
