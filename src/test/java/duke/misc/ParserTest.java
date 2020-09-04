package duke.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidTypeException;

public class ParserTest {
    @Test
    public void invalidCommandTest() {
        try {
            Parser.handleInput("example hello");
        } catch (InvalidTypeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                    e.getMessage());
        } catch (InvalidDescriptionException e) {
            assertEquals("OOPS!!! The description of a task cannot be empty",
                    e.getMessage());
        }
    }
}
