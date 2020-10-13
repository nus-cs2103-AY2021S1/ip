package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testInteract_bye() throws IOException {
        assertEquals("Bye. Hope to see you again soon! \uD83D\uDE0A",
                Parser.parse("bye"));
    }

    @Test
    public void testInteract_find() throws IOException {
        assertEquals("There are no matching tasks in your list!",
                Parser.parse("find book"));
    }

    @Test
    public void testInteract_emptyDescription() throws IOException {
        assertEquals("⚠ OOPS!!! The description of a deadline cannot be empty.",
                Parser.parse("deadline"));
    }

    @Test
    public void testInteract_invalidCommand() throws IOException {
        assertEquals("⚠ OOPS!!! I'm sorry, but I don't know what that means \uD83D\uDE41\n" +
                        "Type \"help\" for a list of commands",
                Parser.parse("blah"));
    }
}
