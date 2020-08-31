package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    private TaskList t = new TaskList();

    private Storage s = new Storage();

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

    @Test
    public void invalidIndexTest() {
        try {
            t.completeTask(-1);
        } catch (InvalidIndexException e) {
            assertEquals("OOPS!!! The index you have chosen is out of bounds",
                    e.getMessage());
        }
    }

    @Test
    public void invalidDataTest() {
        try {
            s.stringToTask("example hello");
        } catch (InvalidTypeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                    e.getMessage());
        } catch (InvalidDataException e) {
            assertEquals("OOPS!!! The data here is invalid!",
                    e.getMessage());
        }
    }
}
