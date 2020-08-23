package duke.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void isValidDone_emptyInput_throwException() {
        try {
            Parser.isValidDone("done ", 5);
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! The task to mark as done cannot be empty.", e.getMessage());
        }
    }
}
