package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class ParserTest {

    private final TaskList taskList = new TaskList();
    private final String filePath = "./data/duke.txt";

    @Test
    public void testInteract_bye() throws IOException {
        assertEquals("Bye. Hope to see you again soon!",
                Parser.parse("bye", taskList, filePath));
    }

    @Test
    public void testInteract_list() throws IOException {
        assertEquals("You have no tasks in your list!",
                Parser.parse("list", taskList, filePath));
    }

    @Test
    public void testInteract_find() throws IOException {
        assertEquals("There are no matching tasks in your list!",
                Parser.parse("find book", taskList, filePath));
    }

    @Test
    public void testInteract_emptyDescription() throws IOException {
        assertEquals("☹ OOPS!!! The description of a deadline cannot be empty.",
                Parser.parse("deadline", taskList, filePath));
    }

    @Test
    public void testInteract_invalidCommand() throws IOException {
        assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                Parser.parse("blah", taskList, filePath));
    }
}
